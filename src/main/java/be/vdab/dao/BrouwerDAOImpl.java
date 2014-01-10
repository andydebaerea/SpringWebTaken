package be.vdab.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import be.vdab.entities.Brouwer;
import be.vdab.valueobjects.Adres;

@Repository
public class BrouwerDAOImpl implements BrouwerDAO {
	private final Map<Long, Brouwer> brouwers = new ConcurrentHashMap<>();
	private final JdbcTemplate jdbcTemplate;
	private final FiliaalRowMapper filiaalRowMapper = new FiliaalRowMapper();
	private final SimpleJdbcInsert simpleJdbcInsert;

	private static final String FIND_ALL_SQL = "select brouwernr, naam, postcode, gemeente, "
			+ "omzet, straat, huisnr from brouwers";
	private static final String FIND_BY_NAAM = FIND_ALL_SQL
			+ " where naam like ?";

	@Autowired
	public BrouwerDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName("brouwers");
		simpleJdbcInsert.usingGeneratedKeyColumns("brouwersNr");
	}

	@Override
	public Iterable<Brouwer> findall() {
		return jdbcTemplate.query(FIND_ALL_SQL, filiaalRowMapper);
	}

	@Override
	public void create(Brouwer brouwer) {
		Map<String, Object> kolomWaarden = new HashMap<>();
		kolomWaarden.put("Naam", brouwer.getNaam());
		kolomWaarden.put("PostCode", brouwer.getAdres().getPostcode());
		kolomWaarden.put("Gemeente", brouwer.getAdres().getGemeente());
		kolomWaarden.put("Omzet", brouwer.getOmzet());
		kolomWaarden.put("Straat", brouwer.getAdres().getStraat());
		kolomWaarden.put("HuisNr", brouwer.getAdres().getHuisNr());
		Number brouwerNr = simpleJdbcInsert.executeAndReturnKey(kolomWaarden);
		brouwer.setBrouwerNr(brouwerNr.longValue());
	}

	@Override
	public Iterable<Brouwer> findByPartOfNaam(String beginNaam) {
		return jdbcTemplate.query(FIND_BY_NAAM, filiaalRowMapper, beginNaam
				+ "%");
	}

	@Override
	public Iterable<Brouwer> findByFirstLetter(char eerteLetter) {
		return jdbcTemplate.query(FIND_BY_NAAM, filiaalRowMapper, eerteLetter
				+ "%");
	}

	private static class FiliaalRowMapper implements RowMapper<Brouwer> {
		@Override
		public Brouwer mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			return new Brouwer(resultSet.getLong("brouwerNr"),
					resultSet.getString("naam"), new Adres(
							resultSet.getString("straat"),
							resultSet.getString("huisNr"),
							resultSet.getInt("postcode"),
							resultSet.getString("gemeente")),
					resultSet.getInt("omzet"));
		}
	}
}

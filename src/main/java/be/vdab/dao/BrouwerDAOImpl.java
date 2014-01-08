package be.vdab.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import be.vdab.entities.Brouwer;
import be.vdab.valueobjects.Adres;

@Repository
public class BrouwerDAOImpl implements BrouwerDAO {
	private final Map<Long, Brouwer> brouwers = new ConcurrentHashMap<>();
	
	public BrouwerDAOImpl() {
		brouwers.put(1L, new Brouwer(1L, "Achouffe", new Adres("Route du Village", "32", 6666, "Achouffe-Wibrin"), 10000));
		brouwers.put(2L, new Brouwer(2L, "Alken", new Adres("Stationstraat", "2", 3570, "Alken"), 950000));
		brouwers.put(3L, new Brouwer(3L, "Bavik", new Adres("Rijksweg", "33", 8531, "Bavikhove"), 11000));
	}
	
	@Override
	public void create(Brouwer brouwer) {
		brouwer.setBrouwerNr(Collections.max(brouwers.keySet()) + 1);
		brouwers.put(brouwer.getBrouwerNr(), brouwer);
	}

	@Override
	public Iterable<Brouwer> findall() {
		return brouwers.values();
	}

	@Override
	public Iterable<Brouwer> findByNaam(String beginNaam) {
		List<Brouwer> brouwersByDeelVanNaam = new ArrayList<>();
		for (Brouwer brouwer : brouwers.values()) {
			if (brouwer.getNaam().toLowerCase().startsWith(beginNaam.toLowerCase())) {
				brouwersByDeelVanNaam.add(brouwer);
			}
		}
		return brouwersByDeelVanNaam;
	}

	@Override
	public Iterable<Brouwer> findByFirstLetter(char eerteLetter) {
		List<Brouwer> brouwersByFirstLetter = new ArrayList<>();
		for (Brouwer brouwer : brouwers.values()) {
			if (brouwer.getNaam().charAt(0) == eerteLetter) {
				brouwersByFirstLetter.add(brouwer);
			}
		}
		return brouwersByFirstLetter;
	}
	
	
}

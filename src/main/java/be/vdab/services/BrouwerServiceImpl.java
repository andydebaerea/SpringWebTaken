package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.BrouwerDAO;
import be.vdab.entities.Brouwer;

@Service
@Transactional(readOnly = true)
public class BrouwerServiceImpl implements BrouwerService {
	private final BrouwerDAO brouwersDAO;
	
	@Autowired
	public BrouwerServiceImpl(BrouwerDAO brouwersDAO) {
		this.brouwersDAO = brouwersDAO;
	}
	
	
	@Override
	@Transactional(readOnly = false)
	public void create(Brouwer brouwer) {
		brouwersDAO.save(brouwer);

	}

	@Override
	public Iterable<Brouwer> findAll() {
		return brouwersDAO.findAll(new Sort("naam"));
	}

	@Override
	public Iterable<Brouwer> findByNaam(String beginNaam) {
		return brouwersDAO.findByNaamStartingWith(beginNaam);
	}
	
	@Override
	public Iterable<Brouwer> findByFirstLetter(String eerteLetter) {
		return brouwersDAO.findByNaamStartingWith(eerteLetter);
	}

}

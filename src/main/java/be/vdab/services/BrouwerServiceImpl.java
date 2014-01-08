package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.vdab.dao.BrouwerDAO;
import be.vdab.entities.Brouwer;

@Service
public class BrouwerServiceImpl implements BrouwerService {
	private final BrouwerDAO brouwersDAO;
	
	@Autowired
	public BrouwerServiceImpl(BrouwerDAO brouwersDAO) {
		this.brouwersDAO = brouwersDAO;
	}
	
	
	@Override
	public void create(Brouwer brouwer) {
		brouwersDAO.create(brouwer);

	}

	@Override
	public Iterable<Brouwer> findAll() {
		return brouwersDAO.findall();
	}

	@Override
	public Iterable<Brouwer> findByNaam(String beginNaam) {
		return brouwersDAO.findByNaam(beginNaam);
	}
	
	@Override
	public Iterable<Brouwer> findByFirstLetter(char eerteLetter) {
		return brouwersDAO.findByFirstLetter(eerteLetter);
	}

}

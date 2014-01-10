package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.BrouwerDAO;
import be.vdab.entities.Brouwer;

@Service
@Transactional(readOnly = true,isolation = Isolation.READ_COMMITTED)
public class BrouwerServiceImpl implements BrouwerService {
	private final BrouwerDAO brouwersDAO;
	
	@Autowired
	public BrouwerServiceImpl(BrouwerDAO brouwersDAO) {
		this.brouwersDAO = brouwersDAO;
	}
	
	
	@Override
	@Transactional(readOnly = false)
	public void create(Brouwer brouwer) {
		brouwersDAO.create(brouwer);

	}

	@Override
	public Iterable<Brouwer> findAll() {
		return brouwersDAO.findall();
	}

	@Override
	public Iterable<Brouwer> findByNaam(String beginNaam) {
		return brouwersDAO.findByPartOfNaam(beginNaam);
	}
	
	@Override
	public Iterable<Brouwer> findByFirstLetter(char eerteLetter) {
		return brouwersDAO.findByFirstLetter(eerteLetter);
	}

}

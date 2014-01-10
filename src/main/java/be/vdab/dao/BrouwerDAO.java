package be.vdab.dao;

import be.vdab.entities.Brouwer;

public interface BrouwerDAO {
	
	public void create(Brouwer brouwer);
	
	public Iterable<Brouwer> findall();
	
	public Iterable<Brouwer> findByPartOfNaam(String beginNaam);
	
	public Iterable<Brouwer> findByFirstLetter(char eerteLetter);
}

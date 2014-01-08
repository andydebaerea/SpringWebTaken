package be.vdab.entities;

import java.io.Serializable;

import be.vdab.valueobjects.Adres;

public class Brouwer implements Serializable {
	private static final long serialVersionUID = 1L;
	private long brouwerNr;
	private String naam;
	private Adres adres;
	private Integer omzet;
	
	public Brouwer(String naam, Adres adres, Integer omzet){
		setNaam(naam);
		setAdres(adres);
		setOmzet(omzet);
	}
	
	public Brouwer(Long brouwerNr, String naam, Adres adres, Integer omzet){
		setBrouwerNr(brouwerNr);
		setNaam(naam);
		setAdres(adres);
		setOmzet(omzet);
	}

	public long getBrouwerNr() {
		return brouwerNr;
	}

	public String getNaam() {
		return naam;
	}

	public Integer getOmzet() {
		return omzet;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setOmzet(Integer omzet) {
		this.omzet = omzet;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public void setBrouwerNr(long brouwerNr) {
		this.brouwerNr = brouwerNr;
	}

	@Override
	public String toString() {
		return "Brouwer [brouwerNr= " + brouwerNr + ", naam= " + naam
				+ ", omzet= " + omzet + "]";
	}
	
	
	
}

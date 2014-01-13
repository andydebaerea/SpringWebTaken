package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import be.vdab.valueobjects.Adres;

@Entity
@Table(name = "Brouwers")
public class Brouwer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long brouwerNr;
	@NotNull
	@Size(min = 1, max = 50, message = "{Size.tekst}")
	private String naam;
	@Embedded
	@NotNull
	@Valid
	private Adres adres;
	@Min(0)
	private Integer omzet;

	public Brouwer() {

	}

	public Brouwer(String naam, Adres adres, Integer omzet) {
		setNaam(naam);
		setAdres(adres);
		setOmzet(omzet);
	}

	public Brouwer(Long brouwerNr, String naam, Adres adres, Integer omzet) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brouwer other = (Brouwer) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}

}

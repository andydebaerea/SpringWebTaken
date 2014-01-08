package be.vdab.web;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BrouwersOpNaamForm {
	@NotNull
	@Size(min = 1, max = 50, message = "{Size.tekst}")
	private String beginNaam;

	public void setBeginNaam(String beginNaam) {
		this.beginNaam = beginNaam.trim();
	}

	public String getBeginNaam() {
		return beginNaam;
	}
}

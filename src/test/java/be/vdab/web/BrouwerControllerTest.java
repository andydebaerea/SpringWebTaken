package be.vdab.web;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.vdab.entities.Brouwer;
import be.vdab.services.BrouwerService;

public class BrouwerControllerTest {
	private BrouwerController brouwerController;
	private Iterable<Brouwer> brouwers;
	private BrouwerService brouwerService;

	@Before
	public void setUp() {
		brouwers = Collections.emptyList();
		brouwerService = Mockito.mock(BrouwerService.class);
		Mockito.when(brouwerService.findAll()).thenReturn(brouwers);
		brouwerController = new BrouwerController(brouwerService);
	}

	@Test
	public void findAllActiveertJuistView() {
		Assert.assertEquals("brouwers/brouwers", brouwerController.findAll()
				.getViewName());
	}

	@Test
	public void findAllMaakRequestAttribuutBrouwers() {
		Assert.assertSame(brouwers, brouwerController.findAll().getModelMap()
				.get("brouwers"));
	}
}

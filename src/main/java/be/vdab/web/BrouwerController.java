package be.vdab.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.BrouwerService;

@Controller
@RequestMapping("/brouwers")
class BrouwerController {
	private final Logger logger = LoggerFactory
			.getLogger(BrouwerController.class);
	private final BrouwerService brouwerService;
	private final char[] alfabet = new char['Z' - 'A' + 1];

	@Autowired
	public BrouwerController(BrouwerService brouwerService) {
		this.brouwerService = brouwerService;
		for (char letter = 'A'; letter <= 'Z'; letter++) {
			alfabet[letter - 'A'] = letter;
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView findAll() {
		return new ModelAndView("brouwers/brouwers", "brouwers",
				brouwerService.findAll());
	}

	@RequestMapping("opnaam")
	public String findOpNaam() {
		return "brouwers/opnaam";
	}

	@RequestMapping(value = "toevoegen", method = RequestMethod.GET)
	public String createForm() {
		return "brouwers/toevoegen";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create() {
		logger.info("Brouwer werd aan database toegevoegd");
		return "redirect:/";
	}

	@RequestMapping("alfabet")
	public ModelAndView findByFirstLetterForm() {
		return new ModelAndView("brouwers/alfabet", "alfabet", alfabet);
	}

	@RequestMapping(value = "alfabet", method = RequestMethod.GET, params = "letter")
	public ModelAndView findByFirstLetter(@RequestParam char letter) {
		ModelAndView modelAndView = new ModelAndView("brouwers/alfabet");
		modelAndView.addObject("alfabet", alfabet);
		modelAndView.addObject("brouwers",
				brouwerService.findByFirstLetter(letter));
		return modelAndView;
	}

	@RequestMapping(value = "opnaam", method = RequestMethod.GET)
	public ModelAndView opNaamForm() {
		return new ModelAndView("brouwers/opnaam", "brouwersOpNaamForm",
				new BrouwersOpNaamForm());
	}

	@RequestMapping(method = RequestMethod.GET, params = "beginNaam")
	public ModelAndView opNaam(@Valid BrouwersOpNaamForm brouwerOpNaamForm,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("brouwers/opnaam");
		if (!bindingResult.hasErrors()) {
			modelAndView
					.addObject("brouwers", brouwerService
							.findByNaam(brouwerOpNaamForm.getBeginNaam()));
		}
		return modelAndView;
	}
}

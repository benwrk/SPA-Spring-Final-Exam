package co.bwsc.assignment3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import co.bwsc.assignment3.database.Pattern;
import co.bwsc.assignment3.database.PatternJDBCTemplate;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	private PatternJDBCTemplate patternJDBCTemplate;

	// Home Mapping
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String homePage() {
		return "home";
	}

	@GetMapping(value = { "/addNewPattern" })
	public ModelAndView addNewPatternForm() {
		Pattern pattern = new Pattern();
		return new ModelAndView("addNewPattern", "pattern", pattern);
	}

	@PostMapping(value = { "/addNewPattern" })
	public ModelAndView addNewPatternSubmit(@ModelAttribute("pattern") Pattern pattern) {
		boolean isValid = (!pattern.getName().isEmpty() && !pattern.getGroup().isEmpty()
				&& !pattern.getImplementation().isEmpty());
		if (isValid) {
			patternJDBCTemplate.create(pattern.getName(), pattern.getGroup(), pattern.getImplementation());
			return new ModelAndView("home", "message", "Successfully added new pattern");
		}
		return new ModelAndView("addNewPattern", "message", "Invalid input!");
	}

	@GetMapping(value = { "/viewPatterns" })
	public ModelAndView patternsListPage(ModelMap model) {
		Map<String, List<Pattern>> patterns = new HashMap<>();
		patterns.put("patterns", patternJDBCTemplate.listPatterns());
		patternJDBCTemplate.listPatterns().forEach(System.out::println);
		return new ModelAndView("viewPatterns", patterns);
	}

	@GetMapping("/editPattern/{id}")
	public ModelAndView editPatternPage(@PathVariable Integer id) {
		Pattern pattern = patternJDBCTemplate.getPattern(id);
		return new ModelAndView("editPattern", "pattern", pattern);
	}

	@PostMapping("/editPattern/{id}")
	public ModelAndView editPatternSubmit(@ModelAttribute Pattern pattern, ModelMap model) {
		patternJDBCTemplate.update(pattern.getId(), pattern.getName(), pattern.getGroup(), pattern.getImplementation());
		return patternsListPage(model.addAttribute("message", "Successfully edited pattern"));
	}

	@PostMapping("deletePattern/{id}")
	public ModelAndView deletePattern(@PathVariable Integer id, ModelMap model) {
		patternJDBCTemplate.delete(id);
		return patternsListPage(model);
	}

}

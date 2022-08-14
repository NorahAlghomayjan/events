package com.norah.events.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.norah.events.models.User;
import com.norah.events.models.requests.UserLogin;
import com.norah.events.services.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userServ;

	// 1
	@GetMapping("/")
	public String index(Model model, @Valid @ModelAttribute("newUser") User newUser, BindingResult result) {
		List<String> locations = Arrays.asList("Riyadh", "Jeddah", "Dammam", "Taif", "Abha", "Qassim");
		model.addAttribute("locations", locations);
		model.addAttribute("newLogin", new UserLogin());
		return "index.jsp";
	}

	// 3
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		// check the obj return from form against the registiration rules
		userServ.register(newUser, result);

		// if register append errors?
		if (result.hasErrors()) {
			// view the page again
			redirectAttributes.addFlashAttribute("newUser", newUser);
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newUser", result);
			return "redirect:/";
		}
		session.setAttribute("user_id", newUser.getId());
		return "redirect:/events";
	}

	// 4
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") UserLogin newLogin, BindingResult result, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		// check the obj returned from form against login validation.
		User user = userServ.login(newLogin, result);

		// if login append errors.
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			redirectAttributes.addFlashAttribute("danger", "You're faild to log-in..");
			return "index.jsp";
		}
		// save user in session
		session.setAttribute("user_id", user.getId());
		redirectAttributes.addFlashAttribute("success", "You're logged in!");
		return "redirect:/events";
	}

	// 5
	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
		session.removeAttribute("user_id");
		redirectAttributes.addFlashAttribute("success", "You're Logged out!");
		return "redirect:/";
	}

}

package com.fishlog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fishlog.database.UserRepository;
import com.fishlog.model.User;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showForm() {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String showProfile(Model model, @RequestParam String username, @RequestParam String password, 
			HttpServletRequest request, HttpServletResponse response) {
		boolean validUser = userRepository.exists(username);
		User user = userRepository.findOne(username);

		if (validUser && BCrypt.checkpw(password, user.getHash())) {
			HttpSession session = request.getSession(true);
			session.setAttribute("currentUser", user.getUsername());
			session.setAttribute("currentUserFishList", user.getFishList());
			return "profile";
		} else {
			model.addAttribute("errorMessage", "Error. Wrong user credentials");
			return "index";
		}
	}

}
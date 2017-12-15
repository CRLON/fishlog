package com.fishlog.controller;

import java.util.ArrayList;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fishlog.database.UserRepository;
import com.fishlog.model.User;

/**
 * 
 * Class handling user registration
 * @author CRL
 *
 */
@Controller
public class RegisterUserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showForm() {
		return "register";
	}

	/**
	 * 
	 * Method used to register and add a new user to database
	 * 
	 * @param model
	 * @param username 
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(Model model, @RequestParam String username, @RequestParam String password) {
		if (username.length() > 0 && password.length() > 0 && !userRepository.exists(username)) {
			userRepository.insert(new User(username, BCrypt.hashpw(password, BCrypt.gensalt(12)), new ArrayList<>()));
			model.addAttribute("successMessage", "User " + username + " successfully created!");
			return "index";
		} else if (username.length() == 0 || password.length() == 0) {
			model.addAttribute("errorMessage", "Error. Username and password fields can not be empty");
			return "register";
		} else {
			model.addAttribute("errorMessage", "Error. " + username + " is not valid");
			return "register";
		}
	}
}
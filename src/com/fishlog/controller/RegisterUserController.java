package com.fishlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fishlog.database.UserRepository;
import com.fishlog.model.User;

/**
 * 
 * Class handling user registration
 * 
 * @author Cristoffer Lönn
 *
 */
@Controller
public class RegisterUserController {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Method mapping for register new user page
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView();
	}

	/**
	 * Method used to register a new user
	 * 
	 * @param modelAndView Used to set URI and Messages
	 * @param user Object to add to database
	 * @param bindingResult Checks for user input errors
	 * @return view URI and messages
	 */
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ModelAndView registerUser(ModelAndView modelAndView, @Validated @ModelAttribute("user")User user, 
			BindingResult bindingResult) {
		if(bindingResult.hasErrors() || user.getUsername().length() == 0 
				|| user.getPassword().length() == 0 || userRepository.exists(user.getUsername())) {
			modelAndView.addObject("errorMessage", "Error. Invalid username");
			modelAndView.setViewName("register");
		} else {
			userRepository.insertNewUser(user);
			modelAndView.addObject("successMessage", "User " + user.getUsername() + " successfully created!");
			modelAndView.setViewName("index");
		} 
		return modelAndView;
	}

}
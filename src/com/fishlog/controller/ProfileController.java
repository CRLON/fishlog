package com.fishlog.controller;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fishlog.database.UserRepository;
import com.fishlog.model.Fish;
import com.fishlog.model.User;

/**
 * 
 * Class handling user profile
 * 
 * @author Cristoffer Lönn
 *
 */
@Controller
@Scope("session")
public class ProfileController {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Method mapping for profile
	 * 
	 * @param modelAndView Used to set URI and Messages
	 * @return view URI and messages
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView redirect(ModelAndView modelAndView) {
		return modelAndView;
	}
	
	/**
	 * Method used to add a fish to logged in user
	 * 
	 * @param modelAndView Used to set URI and Messages
	 * @param fish Object to add
	 * @param bindResult Checks for user input errors
	 * @param session Current session
	 * @return view URI and messages
	 */
	@RequestMapping(value = "/addFish", method = RequestMethod.POST)
	public ModelAndView addFish(ModelAndView modelAndView, @Validated @ModelAttribute("fish")Fish fish, 
			BindingResult bindResult, HttpSession session) {
		modelAndView.setViewName("profile");
		if(bindResult.hasErrors()) {
			modelAndView.addObject("errorMessage", "Please fill in all fields to add a fish!");
		} else {
			User currentUser = userRepository.getCurrentUser(session);
			userRepository.addFish(currentUser, fish);
			session.setAttribute("currentUserFishList", currentUser.getFishList());
		}
		return modelAndView;
	}

	/**
	 * Method used to delete one or more fishes from a User
	 * 
	 * @param modelAndView Holds information used in the view
	 * @param deleteSelected Array fetched from profile.jsp with indexes of fish to delete from users fishlist
	 * @param session Current session
	 * @return view URI and messages
	 */
	@RequestMapping(value = "/deleteFish", method = RequestMethod.POST)
	public ModelAndView removeFish(ModelAndView modelAndView, 
			@RequestParam(required = false) String[] deleteSelected, HttpSession session) {
		User currentUser = userRepository.getCurrentUser(session);
		if(deleteSelected == null) {
			modelAndView.addObject("errorMessage", "Please select one or more fish to delete");
		} else {
			userRepository.deleteFish(currentUser, deleteSelected);
		}
		session.setAttribute("currentUserFishList", currentUser.getFishList());
		modelAndView.setViewName("profile");
		return modelAndView;
	}

	/**
	 * Method used to change user password
	 * 
	 * @param modelAndView Holds information used in the view
	 * @param currentPassword Fetched from profile.jsp
	 * @param newPassword Fetched from profile.jsp
	 * @param session Current session
	 * @return view URI and messages
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ModelAndView changePassword(ModelAndView modelAndView, @RequestParam String currentPassword, 
			@RequestParam String newPassword, HttpSession session) {
		modelAndView.setViewName("profile");
		User currentUser = userRepository.getCurrentUser(session);
		if(BCrypt.checkpw(currentPassword, currentUser.getPassword()) && !newPassword.isEmpty()) {
			currentUser.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt(12)));
			userRepository.save(currentUser);
			modelAndView.addObject("successMessage", "Password successfully changed!");
		} else {
			modelAndView.addObject("errorMessage", "Current password is wrong");
		}
		return modelAndView;
	}

}
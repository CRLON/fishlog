package com.fishlog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fishlog.database.UserRepository;
import com.fishlog.model.Fish;
import com.fishlog.model.User;

/**
 * 
 * Class handling user profile
 * 
 * @author CRL
 *
 */
@Controller
public class ProfileController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String redirect(HttpServletRequest request, HttpServletResponse response) {
		//TODO: Add redirect
		return "profile";
	}

	/**
	 * 
	 * Method used to add a fish to logged in user
	 * 
	 * @param model Used to display error and success messages
	 * @param species of fish to add
	 * @param weight of fish to add
	 * @param length of fish to add
	 * @param request
	 * @param response
	 * @return URL redirect
	 */
	@RequestMapping(value = "/addFish", method = RequestMethod.POST)
	public String addFish(Model model, @RequestParam String species, 
			@RequestParam int weight, @RequestParam int length, HttpServletRequest request, 
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		if(species.length() > 0 && weight > 0 && length > 0) {
			User currentUser = userRepository.findOne(session.getAttribute("currentUser").toString());
			userRepository.addFish(currentUser, new Fish(species, weight, length));
			userRepository.save(currentUser);
			session.setAttribute("currentUserFishList", currentUser.getFishList());
			model.addAttribute("successMessage", "The fish was successfully added!");
		} else {
			model.addAttribute("errorMessage", "Error. One or more fields are empty");
		}
		return "redirect:/profile";
	}

	/**
	 * 
	 * Method used to delete a fish from users database
	 * 
	 * @param model used to display error and success messages
	 * @param request
	 * @param response
	 * @return URL redirect
	 */

	@RequestMapping(value = "/deleteFish", method = RequestMethod.POST)
	public String removeFish(Model model, HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(true);
		User currentUser = userRepository.findOne(session.getAttribute("currentUser").toString());

		String[] idsToDelete = request.getParameterValues("deleteSelected");
		if(idsToDelete != null) {
			userRepository.deleteFish(currentUser, idsToDelete);
			userRepository.save(currentUser);
			model.addAttribute("successMessage", "" + idsToDelete.length + " fish successfully deleted!");
		} else {
			model.addAttribute("errorMessage", "Please select one or more entrys to delete.");
		}
		session.setAttribute("currentUserFishList", currentUser.getFishList());
		return "redirect:/profile";
	}

}
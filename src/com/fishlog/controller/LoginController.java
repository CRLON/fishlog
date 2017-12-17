package com.fishlog.controller;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fishlog.database.UserRepository;
import com.fishlog.model.User;

/**
 * Class handling user login
 * 
 * @author Cristoffer Lönn
 *
 */
@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	/**
	 * Method mapping for index
	 * 
	 * @return URI for index view
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showIndex() {
		return new ModelAndView("index");
	}

	/**
	 * Method handling user login
	 * 
	 * @param modelAndView holds information used in the view
	 * @param username fetched from index.jsp
	 * @param password fetched from index.jsp
	 * @param session current session to bind user and fish list to
	 * @return view URI and messages
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView doLogin(ModelAndView modelAndView, @RequestParam String username, 
			@RequestParam String password, HttpSession session) {

		boolean validUser = userRepository.exists(username);
		User user = userRepository.findOne(username);
		if (validUser && BCrypt.checkpw(password, user.getPassword())) {
			session.setAttribute("currentUser", user);
			session.setAttribute("currentUserFishList", user.getFishList());
			modelAndView.setViewName("profile");
		} else {
			modelAndView.addObject("errorMessage", "Error. Invalid user credentials.");
			modelAndView.setViewName("index");
		}
		return modelAndView;
	}

}
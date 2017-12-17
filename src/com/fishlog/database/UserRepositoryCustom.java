package com.fishlog.database;

import javax.servlet.http.HttpSession;

import com.fishlog.model.Fish;
import com.fishlog.model.User;

/**
 * Interface with custom add and delete method for this project
 * 
 * @author Cristoffer Lönn
 *
 */
public interface UserRepositoryCustom {

	/**
	 * Adds a fish to the current users fish list
	 * @param currentUser User to add a fish to
	 * @param fish object of the type Fish to add
	 */
	void addFish(User currentUser, Fish fish);

	/**
	 * Deletes fish from the current users fish list
	 * @param currentUser User to delete fish from
	 * @param deleteSelected Array of strings which contains id's of fish to delete from the current user
	 */
	void deleteFish(User currentUser, String[] deleteSelected);

	/**
	 * Gets the current user from the session
	 * @param session the current session
	 * @return The user that is currently logged in
	 */
	User getCurrentUser(HttpSession session);

	/**
	 * Inserts a new user to the database
	 * @param user Object to add
	 */
	void insertNewUser(User user);
}
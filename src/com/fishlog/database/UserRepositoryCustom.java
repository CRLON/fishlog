package com.fishlog.database;

import com.fishlog.model.Fish;
import com.fishlog.model.User;

/**
 * Interface with custom add and delete method for this project
 * 
 * @author CRL
 *
 */
public interface UserRepositoryCustom {

	void addFish(User currentUser, Fish fish);
	void deleteFish(User currentUser, String[] idsToDelete);
	//User getCurrentUser(HttpSession session);

}
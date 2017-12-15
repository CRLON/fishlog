package com.fishlog.database;

import com.fishlog.model.Fish;
import com.fishlog.model.User;

public interface UserRepositoryCustom {

	void addFish(User currentUser, Fish fish);
	void deleteFish(User currentUser, String[] idsToDelete);
	//User getCurrentUser(HttpSession session);

}
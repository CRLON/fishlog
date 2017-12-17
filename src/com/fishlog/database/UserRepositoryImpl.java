package com.fishlog.database;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.fishlog.model.Fish;
import com.fishlog.model.User;

/**
 * Implementation of custom UserRepository methods
 * 
 * @author Cristoffer Lönn
 *
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void addFish(User currentUser, Fish fish) {
		currentUser.getFishList().add(fish);
		mongoTemplate.save(currentUser);	
	}

	@Override
	public void deleteFish(User currentUser, String[] deleteSelected) {
			for(int i = deleteSelected.length; i > 0; i--) {
				currentUser.getFishList().remove(Integer.parseInt(deleteSelected[i-1]));
			}
		mongoTemplate.save(currentUser);
	}

	@Override
	public User getCurrentUser(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		return currentUser;
	}

	@Override
	public void insertNewUser(User user) {
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
		user.setFishList(new ArrayList<>());
		mongoTemplate.insert(user);
	}

}
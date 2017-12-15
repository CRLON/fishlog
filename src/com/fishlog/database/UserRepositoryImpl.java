package com.fishlog.database;

import com.fishlog.model.Fish;
import com.fishlog.model.User;

/**
 * Implementation of custom UserRepository methods
 * 
 * @author CRL
 *
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

	@Override
	public void addFish(User currentUser, Fish fish) {
		currentUser.getFishList().add(fish);
	}

	@Override
	public void deleteFish(User currentUser, String[] idsToDelete) {
			for(int i = idsToDelete.length; i > 0; i--) {
				currentUser.getFishList().remove(Integer.parseInt(idsToDelete[i-1]));
			}
	}

}
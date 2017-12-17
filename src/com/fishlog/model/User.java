package com.fishlog.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

/**
 * User entity
 * 
 * @author Cristoffer Lönn
 *
 */
@Component
@Scope("session")
@Document(collection = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -175013064895498871L;
	@Id
	private String username;
	private String password;
	private List<Fish> fishList;

	public User() {
	}

	public User(String username, String password, List<Fish> fishList) {
		this.username = username;
		this.password = password;
		this.fishList = fishList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Fish> getFishList() {
		return fishList;
	}

	public void setFishList(List<Fish> fishList) {
		this.fishList = fishList;
	}

}
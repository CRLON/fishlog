package com.fishlog.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -175013064895498871L;
	@Id
	private String username;
	private String hash;
	private List<Fish> fishList;

	public User() {
	}

	public User(String username, String hash, List<Fish> fishList) {
		this.username = username;
		this.hash = hash;
		this.fishList = fishList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public List<Fish> getFishList() {
		return fishList;
	}

	public void setFishList(List<Fish> fishList) {
		this.fishList = fishList;
	}

}
package com.yonaxtics.gymwer.set.person.entity;

import com.yonaxtics.gymwer.set.master.entity.*;
import com.yonaxtics.gymwer.set.location.entity.*;
import com.yonaxtics.gymwer.util.base.entity.Entity;
import com.yonaxtics.gymwer.set.user.entity.User;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class Person extends Entity {

	private User user;
	private Location location;
	private Role role;
	private String document;
	private String name;
	private String email;

	
	public Person(int id) {
		
		super(id);		
	}
	
	public Person(String email){
		
		super(0);
		this.email = email;		
	}	
		
	public Person(String email, User user){
		
		super(0);
		this.email = email;
		this.user = user;
	}	

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}


	

}

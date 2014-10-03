package com.yonaxtics.gymwer.set.person.entity;

import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.set.picture.entity.Picture;
import com.yonaxtics.gymwer.util.base.entity.Entity;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
@SuppressWarnings("serial")
public abstract class Person  extends Entity {
	
	public final static int FEMALE = 6;
	public final static int  MALE = 5;	

		
	protected String document;
	protected String name;
	protected String lastName;	
	protected Location location;
	protected transient Login login;
	protected Picture picture;
	
	public Person(int id) {		
		super(id);		
	}
		
	public Person(){		
		super(0);		
	}
	
    public String getFullName(){
    	return name.concat(" ").concat(lastName);
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


	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return the login
	 */
	public Login getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}















	

}

package com.yonaxtics.gymwer.set.person.entity;

import java.util.List;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
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

	protected Location location;	
	protected String document;
	protected String name;
	protected String lastName;
	protected List<Gym> listGym;
	protected Gym gym;
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

	public List<Gym> getListGym() {
		return listGym;
	}

	public void setListGym(List<Gym> listGym) {
		this.listGym = listGym;
	}

	public Gym getGym() {
		return gym;
	}

	public void setGym(Gym gym) {
		this.gym = gym;
	}















	

}

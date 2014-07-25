package com.gymwer.dpa.entity;

import java.util.List;

import com.gymwer.set.entity.Person;
import com.gymwer.set.entity.location.Location;
import com.gymwer.util.base.entity.Entity;

public class Gym extends Entity {

	private String nit;
	private String name;
	private Person contact;
	private Location location;
	private List<Site> siteList;
	private List<Client> clientList;
	
	public Gym(int id) {
		
		super(id);
		
	}
	
	public String getNit() {
		return nit;
	}



	public void setNit(String nit) {
		this.nit = nit;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Person getContact() {
		return contact;
	}



	public void setContact(Person contact) {
		this.contact = contact;
	}




	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Site> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<Site> siteList) {
		this.siteList = siteList;
	}

	public List<Client> getClientList() {
		return clientList;
	}

	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}




	
	

}

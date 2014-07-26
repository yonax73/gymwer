package com.yonaxtics.gymwer.dpa.gym.entity;

import java.util.List;

import com.yonaxtics.gymwer.set.person.entity.*;
import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.util.base.entity.Entity;
import com.yonaxtics.gymwer.dpa.client.entity.*;
import com.yonaxtics.gymwer.dpa.site.entity.*;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
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

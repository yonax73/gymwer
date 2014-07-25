package com.gymwer.dpa.entity;

import java.util.List;

import com.gymwer.set.entity.Person;
import com.gymwer.set.entity.location.Location;
import com.gymwer.util.base.entity.Entity;

public class Site extends Entity {
	
	private Location location;
	private Person Contact;
	private String nit;
	private String name;
	private List<Plan> planList;
	
	public Site(int id) {
		
		super(id);
		
	}

	public Location getLocation() {
		return location;
	}

	public Person getContact() {
		return Contact;
	}

	public String getNit() {
		return nit;
	}

	public String getName() {
		return name;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setContact(Person contact) {
		Contact = contact;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Plan> getPlanList() {
		return planList;
	}

	public void setPlanList(List<Plan> planList) {
		this.planList = planList;
	}

}

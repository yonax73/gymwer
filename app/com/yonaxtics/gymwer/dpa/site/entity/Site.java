package com.yonaxtics.gymwer.dpa.site.entity;

import java.util.List;

import com.yonaxtics.gymwer.set.person.entity.*;
import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.dpa.plan.entity.*;
import com.yonaxtics.gymwer.util.base.entity.Entity;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
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

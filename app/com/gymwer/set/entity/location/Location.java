package com.gymwer.set.entity.location;

import akka.actor.Address;

import com.gymwer.util.base.entity.Entity;

public class Location extends Entity {




	private Phone phone;
	private Address address;
	
	
	public Location(int id) {

		super(id);

	}
	
	
	public Phone getPhone() {
		return phone;
	}


	public Address getAddress() {
		return address;
	}


	public void setPhone(Phone phone) {
		this.phone = phone;
	}


	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}

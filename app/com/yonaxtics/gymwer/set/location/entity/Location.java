package com.yonaxtics.gymwer.set.location.entity;


import com.yonaxtics.gymwer.util.base.entity.*;
import com.yonaxtics.gymwer.set.master.entity.*;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class Location extends Entity {

	private static final long serialVersionUID = 1L;
	private Phone phone;
	private Address address;
	
	
	public Location(int id) {

		super(id);

	}
	
	
	public Location (int id,Phone phone, Address address){
		
		super(id);
		this.phone = phone;
		this.address = address;
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

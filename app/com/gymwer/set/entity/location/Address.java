package com.gymwer.set.entity.location;

import com.gymwer.set.entity.MasterValue;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class Address extends MasterValue {

	
	private City city;
	
	public Address(int id) {
		
		super(id);
		
	}
	
	public Address(int id, String address){
		
		super(id);
		setValue1(address);
	}
	
	public Address(int id, String address, int CityId){
		
		super(id);
		setValue1(address);
		city = new City(CityId);
	}
	
	
	public void setAddress(String address){
		
		setValue1(address);
	}
	
	public String getAddress(){
		
		return getValue1();
		
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	

	
	

}

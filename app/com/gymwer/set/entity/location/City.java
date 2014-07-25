package com.gymwer.set.entity.location;

import com.gymwer.set.entity.MasterValue;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class City  extends MasterValue{

	private Country country;
	
	public City(int id) {
		
		super(id);
		
	}
	
	public City(int id, String name){
		
		super(id);
		setValue1(name);
		
	}
	
	public City(int id, String name, int countryId){
		
		super(id);
		setValue1(name);
		country = new Country(countryId);
		
	}
	
	public String getName(){
		
		return getValue1();
	}
	
	public void setName(String name){
		
		setValue1(name);
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	

   
	
}

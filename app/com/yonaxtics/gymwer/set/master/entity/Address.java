package com.yonaxtics.gymwer.set.master.entity;




/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class Address extends MasterValue {

	private static final long serialVersionUID = 1L;
	private City city;
	
	public Address(int id) {		
		super(id);
		setMasterId(Master.ADDRESS_ID);		
	}
	
	public Address(int id,String address){		
		super(id);
		setValue1(address);
		setMasterId(Master.ADDRESS_ID);
	}
	public Address(String address){		
		super(0);
		setValue1(address);
		setMasterId(Master.ADDRESS_ID);
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

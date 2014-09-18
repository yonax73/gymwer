package com.yonaxtics.gymwer.set.master.entity;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
@SuppressWarnings("serial")
public class Address extends MasterValue {
	
	private City city;
	
	public Address(int id) {		
		super(id);
		setMasterId(IMaster.ADDRESS_ID);		
	}
	
	public Address(int id,String address){		
		super(id);
		setValue1(address);
		setMasterId(IMaster.ADDRESS_ID);
	}
	public Address(String address){		
		super(0);
		setValue1(address);
		setMasterId(IMaster.ADDRESS_ID);
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

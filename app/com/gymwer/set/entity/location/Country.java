package com.gymwer.set.entity.location;

import com.gymwer.set.entity.MasterValue;


/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class Country extends MasterValue{

	public Country(int id) {
		
		super(id);
		
	}
	
    public Country(int id, String name){
    	
    	super(id);
    	setValue1(name);
    	
    }
    
    public String getName(){
    	
    	return getValue1();
    }
    
    public void setName(String name){
    	
    	setValue1(name);
    }
	
}

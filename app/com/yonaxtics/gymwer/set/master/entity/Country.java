package com.yonaxtics.gymwer.set.master.entity;




/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class Country extends MasterValue{
	
	private static final long serialVersionUID = 1L;

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

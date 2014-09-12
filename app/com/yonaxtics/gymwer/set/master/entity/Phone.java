package com.yonaxtics.gymwer.set.master.entity;




/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class Phone extends MasterValue {

	

	public Phone(int id) {		
		super(id);
		setMasterId(Master.PHONE_ID);	
	}
	
	public Phone(int id,String phone){
		
		super(id);
		setValue1(phone);
		setMasterId(Master.PHONE_ID);
	}
	
	public Phone(String phone){
		
		super(0);
		setValue1(phone);
		setMasterId(Master.PHONE_ID);
	}
	
	public void setPhone(String phone){
		
		setValue1(phone);
		
	}
	
	public String getPhone(){
		
		return getValue1();
	}

}

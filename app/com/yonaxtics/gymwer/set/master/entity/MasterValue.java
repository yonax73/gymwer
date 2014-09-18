package com.yonaxtics.gymwer.set.master.entity;

import com.yonaxtics.gymwer.util.base.entity.Entity;


/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
@SuppressWarnings("serial")
public abstract class  MasterValue   extends Entity	{
    
	protected int masterId;
	protected String value1;
	protected String value2;
	protected String value3;	
	
	public MasterValue(int id) {		
		super(id);		
	}
	
	@Override
	public boolean isEmpty() {	
		return value1 == null || value1 =="";
	}
   
	public int getMasterId() {
		return masterId;
	}


	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}


	public String getValue1() {
		return value1;
	}


	public void setValue1(String value1) {
		this.value1 = value1;
	}


	public String getValue2() {
		return value2;
	}

	
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	
	
	public String getValue3() {
		return value3;
	}


	public void setValue3(String value3) {
		this.value3 = value3;
	}



}

package com.yonaxtics.gymwer.set.master.entity;

import com.yonaxtics.gymwer.util.base.entity.Entity;


/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class MasterValue  extends Entity{

	
	private int masterId;
	private String value1;
	private String value2;
	private int keyId;
	
	
	public MasterValue(int id) {
		
		super(id);
		
	}
	
	
	public boolean isEmpty(){
		
		return value1 == null || value1 == "" || value2 == null || value2 == "";
		
	}
	
	public boolean hasKey(){
		
		return keyId > 0;
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


	public int getKeyId() {
		return keyId;
	}


	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}

}

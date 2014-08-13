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
	private String value3;
	private int data1;
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


	protected int getMasterId() {
		return masterId;
	}


	protected void setMasterId(int masterId) {
		this.masterId = masterId;
	}


	protected String getValue1() {
		return value1;
	}


	protected void setValue1(String value1) {
		this.value1 = value1;
	}


	protected String getValue2() {
		return value2;
	}

	
	protected void setValue2(String value2) {
		this.value2 = value2;
	}
	
	
	protected String getValue3() {
		return value3;
	}


	protected void setValue3(String value3) {
		this.value3 = value3;
	}


	protected int getData1() {
		return data1;
	}


	protected void setData1(int data1) {
		this.data1 = data1;
	}


	protected int getKeyId() {
		return keyId;
	}


	protected void setKeyId(int keyId) {
		this.keyId = keyId;
	}

}

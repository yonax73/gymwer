package com.yonaxtics.gymwer.dpa.plan.entity;

import com.yonaxtics.gymwer.util.base.entity.Entity;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
@SuppressWarnings("serial")
public class Plan extends Entity {
	
	private String code;
	private String name;
	private double price;
	private int daysExpire;
	private String notes;	

	public Plan(int id) {		
		super(id);		
	}

	@Override
	public boolean isEmpty() {	
		return name== null || name == "";
	}

	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}

	public String getNotes() {
		return notes;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getDaysExpire() {
		return daysExpire;
	}

	public void setDaysExpire(int daysExpire) {
		this.daysExpire = daysExpire;
	}	

}

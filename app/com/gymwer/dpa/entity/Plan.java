package com.gymwer.dpa.entity;

import com.gymwer.util.base.entity.Entity;

public class Plan extends Entity {
	
	private String code;
	private String name;
	private double price;
	private String notes;
	
	
	

	public Plan(int id) {
		
		super(id);
		
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
	
	

}

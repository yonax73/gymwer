package com.gymwer.set.entity;

import com.gymwer.util.base.entity.Entity;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class Master extends Entity{

	private String name;
	private String description;
	
	public Master(int id) {
		
		super(id);
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}

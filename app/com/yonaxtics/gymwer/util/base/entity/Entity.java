package com.yonaxtics.gymwer.util.base.entity;

import java.time.*;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class Entity {

	
	


	protected int id;
	protected boolean active;
	protected LocalDateTime created;
	
	
	
	
	
	public Entity(int id){
		
		this.id = id;
		
	}
	
	
	
	
		

	
	
	
	
	public boolean exists(){
		
		return id > 0;
		
	}
	
	
	
	

	
	
	
	public boolean isValid(){
		
		return   exists() && isActive();
	}
	
	
	
	
	public boolean equals(Entity entity){
		
		return id  == entity.getId();
	}
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
}

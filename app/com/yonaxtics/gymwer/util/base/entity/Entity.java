package com.yonaxtics.gymwer.util.base.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public abstract class  Entity implements Serializable {	

	private static final long serialVersionUID = 1L;
	protected int id;
	protected boolean active;
	protected LocalDateTime created;	
	public final static byte ACTIVE = 1;
	public final static byte INACTIVE = 0;
	public final static byte ALL = -1;
	
	public Entity(int id){		
		this.id = id;		
	}
	
    public abstract boolean isEmpty(); 
	
	public boolean exists(){		
		return id > 0;		
	}	
	
	public boolean isValid(){		
		return   id > 0 && active;
	}	
	
	public boolean equals(Entity entity){		
		return id  == entity.getId();
	}
	
	public String getFormatCreated(){
		if(created!=null)
		return created.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		return "No Date";
	}

	
	public String getSerial(){
		return new String(this.getClass().getName().concat(":").concat(String.valueOf(id)));
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

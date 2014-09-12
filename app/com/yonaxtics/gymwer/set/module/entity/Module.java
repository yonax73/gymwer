package com.yonaxtics.gymwer.set.module.entity;


import java.util.ArrayList;
import java.util.List;

import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.util.base.entity.Entity;

/** 
 * Clase     : Module.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 13, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class Module extends Entity {

	private static final long serialVersionUID = 1L;
	
	public final static int PARENT =1;
    public final static int CHILD =2;  
    
	private String description;
	private int rolId;
	private List<Action> children;
	private Module parent;
	
	
	/**
	 * @param id
	 */
	public Module(int id) {
		super(id);
		
	}
	
	public Module(int id, String description, int rolId) {
		super(id);
		this.description = description;
		this.rolId = rolId;
		
		if(isParent()){
			
			children = new ArrayList<Action>();
			
		}
		
	}	
	
	
	public Module(int id, String description) {
		super(id);
		setDescription(description);
	
	}
	
	
	public Module(String description) {
		super(0);
		setDescription(description);
	}

	public boolean isChild(){		
		return rolId == CHILD;		
	}
	
	
	public boolean isParent(){		
		return rolId == PARENT;		
	}
	
	
	
	public void releaseParent(){
		
		setParent(null);
	}
	
	
	public String getDescription(){
		
		return description;
	}	
	
	public void setDescription(String description){
		
		this.description = description;
	}
	

	public List<Action> getChildren() {
		return children;
	}

	public void setChildren(List<Action> children) {
		this.children = children;
	}

	public Module getParent() {
		return parent;
	}

	public void setParent(Module parent) {
		this.parent = parent;
	}

	public int getRolId() {
		return rolId;
	}

	public void setRolId(int rolId) {
		this.rolId = rolId;
	}
	
	




	
	

}

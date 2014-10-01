package com.yonaxtics.gymwer.set.module.entity;


import java.util.ArrayList;
import java.util.List;

import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.set.master.entity.ModuleType;
import com.yonaxtics.gymwer.util.base.entity.Entity;

/** 
 * Clase     : Module.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 13, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

@SuppressWarnings("serial")
public class Module extends Entity {
	
    
	private String description;
	private List<Action> children;
	private Module parent;	
	private ModuleType moduleType;
	
	/**
	 * @param id
	 */
	public Module(int id) {
		super(id);
		
	}
	
	public Module(int id, String description, ModuleType moduleType) {
		super(id);
		this.description = description;
		this.moduleType = moduleType;	
		if(moduleType.isParent()){			
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
	
	@Override
	public void copy(Entity entity) {
	
		
	}
	
	@Override
	public boolean isEmpty() {	
		return description == null || description =="";
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

	public ModuleType getModuleType() {
		return moduleType;
	}

	public void setModuleType(ModuleType moduleType) {
		this.moduleType = moduleType;
	}
	
	




	
	

}

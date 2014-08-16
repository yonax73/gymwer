package com.yonaxtics.gymwer.set.module.entity;

import java.util.ArrayList;
import java.util.List;

import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.set.master.entity.MasterValue;

import static com.yonaxtics.gymwer.util.Constant.MODULE_CHILD;
import static com.yonaxtics.gymwer.util.Constant.MODULE_PARENT;

/** 
 * Clase     : Module.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 13, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class Module extends MasterValue {

	
	private List<Action> children;
	private Module parent;
	
	/**
	 * @param id
	 */
	public Module(int id) {
		super(id);
		
	}
	
	public Module(int id, String description, int state) {
		super(id);
		setDescription(description);
		setState(state);
		
		if(isParent()){
			
			children = new ArrayList<Action>();
			
		}
		
	}	
	
	
	public Module(int id, String description) {
		super(id);
		setDescription(description);
	
	}
	
	
	public boolean isChild(){
		
		return getState() == MODULE_CHILD;
		
	}
	
	
	public boolean isParent(){
		
		return getState() == MODULE_PARENT;
		
	}
	
	
	
	public void releaseParent(){
		
		setParent(null);
	}
	
	
	public String getDescription(){
		
		return getValue1();
	}	
	
	public void setDescription(String description){
		
		setValue1(description);
	}
	
	
	public int getState(){
		
		return getData1();
	}
	
	public void setState(int state){
		
		setData1(state);
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
	
	




	
	

}

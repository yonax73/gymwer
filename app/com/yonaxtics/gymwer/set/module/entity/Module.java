package com.yonaxtics.gymwer.set.module.entity;

import java.util.List;

import com.yonaxtics.gymwer.set.master.entity.MasterValue;
import static com.yonaxtics.gymwer.util.Constant.MODULE_CHILD;

/** 
 * Clase     : Module.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 13, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class Module extends MasterValue {

	
	private List<Module> childrens;
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
	}
	
	
	public Module(int id, String description) {
		super(id);
		setDescription(description);
	
	}
	
	
	public boolean isChild(){
		
		return getState() == MODULE_CHILD;
		
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

	public List<Module> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Module> childrens) {
		this.childrens = childrens;
	}

	public Module getParent() {
		return parent;
	}

	public void setParent(Module parent) {
		this.parent = parent;
	}
	
	




	
	

}

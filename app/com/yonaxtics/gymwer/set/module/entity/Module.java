package com.yonaxtics.gymwer.set.module.entity;

import java.util.List;

import com.yonaxtics.gymwer.set.master.entity.MasterValue;
import com.yonaxtics.gymwer.set.action.entity.*;

/** 
 * Clase     : Module.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 13, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class Module extends MasterValue {

	
	private Module parent;
	private List<Action> actionList;
	
	/**
	 * @param id
	 */
	public Module(int id) {
		super(id);
		
	}
	
	
	
	public String getDescription(){
		
		return getValue1();
	}	
	
	public void setDescription(String description){
		
		setValue1(description);
	}



	public Module getParent() {
		return parent;
	}


	public void setParent(Module parent) {
		this.parent = parent;
	}



	public List<Action> getActionList() {
		return actionList;
	}



	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}
	
	

}

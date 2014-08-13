package com.yonaxtics.gymwer.set.permission.entity;

import java.util.List;

import com.yonaxtics.gymwer.util.base.entity.Entity;
import com.yonaxtics.gymwer.set.action.entity.*;

/** 
 * Clase     : Permission.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 13, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class Permission extends Entity {

	
	
	private List<Action> actionList;
	/**
	 * @param id
	 */
	public Permission(int id) {
		super(id);
		
	}
	
	public List<Action> getActionList() {
		return actionList;
	}
	
	public void setActionList(List<Action> actionList) {
		this.actionList = actionList;
	}
	
	

}

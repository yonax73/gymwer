package com.yonaxtics.gymwer.sec.permission.entity;

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

@SuppressWarnings("serial")
public class Permission extends Entity {	
	
	private Action action;	
	/**
	 * @param id
	 */
	public Permission(int id) {
		super(id);		
	}
	
	@Override
	public void copy(Entity entity) {
	
		
	}
	
	@Override
	public boolean isEmpty() {	
		return action == null;
	}
	
	public Permission(Action action){
		super(0);
		this.action = action;
	}
	
	public Action getAction() {
		return action;
	}
	
	
	public void setAction(Action action) {
		this.action = action;
	}
	
	
	
	

}

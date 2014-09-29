package com.yonaxtics.gymwer.set.action.entity;

import com.yonaxtics.gymwer.set.master.entity.ActionType;
import com.yonaxtics.gymwer.set.module.entity.Module;
import com.yonaxtics.gymwer.util.base.entity.Entity;

/** 
 * Clase     : Action.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 13, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

@SuppressWarnings("serial")
public class Action extends Entity {	
    
    public final static int CREATE_CLIENT = 1; 
    public final static int UPDATE_CLIENT = 2; 
    public final static int DELETE_CLIENT = 3; 
    public final static int CREATE_PLANS = 4; 
    public final static int UPDATE_PLANS = 5; 
    public final static int DELETE_PLANS = 6; 
    public final static int ACTIVE_INACTIVE_CLIENT = 7; 
    public final static int ACTIVE_INACTIVE_PLAN = 8; 
    public final static int CREATE_USER = 9; 
    public final static int UPDATE_USER = 10; 
    public final static int ACTIVE_INACTIVE_USER = 11; 
    public final static int DELETE_USER = 12; 
    public final static int CREATE_ROLE = 13; 
    public final static int UPDATE_ROLE = 14; 
    public final static int ACTIVE_INACTIVE_ROLE = 15; 
    public final static int DELETE_ROLE = 16; 
    public final static int LOAD_CLIENTS = 17; 
    public final static int LOAD_DASHBOARD = 18; 
    public final static int LOAD_PLANS = 19; 
    public final static int SHOW_RESOURCES = 20; 
    public final static int LOAD_USERS = 21; 
    public final static int LOAD_ROLES = 22; 
    public final static int LOAD_MESSAGES = 23;
    public final static int LOAD_SETTINGS = 24;
    public final static int LOAD_USER = 25;
    public final static int LOAD_GYM = 26;
    public static final int UPDATE_GYM = 27;
    
    public final static String ACTIONS_LOAD_LIST_KEY = "ACTIONS_LOAD_LIST_KEY";
	
	
	private String description;
	private String url;
	private String ico;
	private Module module;	
	private ActionType actionType;

	/**
	 * @param id
	 */
	public Action(int id) {		
		super(id);		
	}
	
	public Action(String url,String ico, Module module) {		
		super(0);
		this.url = url;
		this.ico = ico;
		this.module = module;
	}	
	
	public Action(int id,String url) {		
		super(id);
		this.url = url;
	}
	
	@Override
	public boolean isEmpty() {
	    return description == null || description == "";
	}
	@Override
	public void copy(Entity entity) {
	
		
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}


	/**
	 * @return the ico
	 */
	public String getIco() {
		return ico;
	}


	/**
	 * @param ico the ico to set
	 */
	public void setIco(String ico) {
		this.ico = ico;
	}


	/**
	 * @return the module
	 */
	public Module getModule() {
		return module;
	}


	/**
	 * @param module the module to set
	 */
	public void setModule(Module module) {
		this.module = module;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}



	
}

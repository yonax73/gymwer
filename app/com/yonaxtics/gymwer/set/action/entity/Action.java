package com.yonaxtics.gymwer.set.action.entity;

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

	public final static int LOAD_USER = 25;
    public final static int LOAD_GYM = 26;   
    
	
	private String description;
	private String url;
	private String ico;
	private Module module;	

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



	
}

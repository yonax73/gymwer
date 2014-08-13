package com.yonaxtics.gymwer.set.action.entity;

import com.yonaxtics.gymwer.set.master.entity.MasterValue;

/** 
 * Clase     : Action.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 13, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class Action extends MasterValue {

	
	
	
	
	/**
	 * @param id
	 */
	public Action(int id) {
		
		super(id);
		
	}
	
	
	public String getDescription(){
		
		return getValue1();
	}
	
	
	public void setDescription(String description){
		
		setValue1(description);
	}
	
	public String getUrl(){
		
		return getValue2();
	}
	
	
	public void setUrl(String url){
		
		setValue2(url);
		
	}
	
	
    public String getIcon(){
    	
    	return getValue3();
    }
    
    
    public void setIcon(String icon){
    	
    	setValue3(icon);
    }
	
}

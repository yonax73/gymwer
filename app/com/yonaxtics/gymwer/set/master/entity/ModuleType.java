package com.yonaxtics.gymwer.set.master.entity;

/** 
 * Class     : ModuleType.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 22, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

@SuppressWarnings("serial")
public class ModuleType extends MasterValue {

	public final static int SINGLE =0;
	public final static int PARENT =1;
    public final static int CHILD =2; 
	
	/**
	 * @param id
	 */
	public ModuleType(int id) {
		super(id);
		
	}
	
	public boolean isChild(){		
		return id == CHILD;		
	}	
	
	public boolean isParent(){		
		return id == PARENT;		
	}	
	
	

}

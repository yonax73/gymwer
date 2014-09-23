package com.yonaxtics.gymwer.set.permission.logic;


import com.yonaxtics.gymwer.set.permission.dao.PermissionDao;
import com.yonaxtics.gymwer.set.user.entity.User;

/** 
 * Class     : PermissionLogic.java.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 15, 2014<br/> 
 * User      : YQ<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class PermissionLogic {
	/**
	 * @param contact
	 */
	public static boolean load(User user) {		
		boolean result = false;
		if(user != null && user.exists()){		  	
		  result = 	PermissionDao.load(user);		  
		  if(result){		 			  
			  user.getRole().addChildrenToParentsModules();		
			  user.createNavigation();
		  }		  
		}
		return result;		
	}
	


}

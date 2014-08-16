package com.yonaxtics.gymwer.set.permission.logic;

import com.yonaxtics.gymwer.set.permission.dao.PermissionDao;
import com.yonaxtics.gymwer.set.person.entity.Person;

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
	public static boolean loadNav(Person contact) {
		
		boolean result = false;
		if(contact != null && contact.exists()){			
		  result = 	PermissionDao.loadNav(contact);		  
		  if(result){		 			  
			  contact.getUser().getRole().arrange();
		  }		  
		}
		return result;
		
	}
	


}

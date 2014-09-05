package com.yonaxtics.gymwer.set.profile.logic;

import com.yonaxtics.gymwer.set.person.entity.Person;
import com.yonaxtics.gymwer.set.profile.dao.ProfileDao;

/** 
 * Clase     : ProfileLogic.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 4, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class ProfileLogic  {
	
	public static boolean load(Person contact){		
		boolean result = false;		
		if(contact != null && contact.exists()){			
			result = ProfileDao.load(contact);
			contact.setId(0);
		}		
		return result;
	}
}

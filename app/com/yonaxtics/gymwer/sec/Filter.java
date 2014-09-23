package com.yonaxtics.gymwer.sec;

import java.lang.reflect.Method;



/** 
 * Class     : Filter.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 19, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class Filter extends SecuredController { 

	
	private final static String ACTIONS_EXCLUDES[] = {
			"login",
			"signUp",
			"createAccount",
			"signIn",
			"signOut"
	};	
	private final static int ACIONS_EXCLUDE_LENGTH = ACTIONS_EXCLUDES.length;	
	
	public static boolean filter_action(Method actionMethod){
		boolean result = true;
		if(actionMethod!=null){
			final String action = actionMethod.getName();
			if(action!=null && !action.isEmpty()){
				int i = 0;			
				do {
					if(action.equals(ACTIONS_EXCLUDES[i++]))result = false;					
				} while (i < ACIONS_EXCLUDE_LENGTH && result);
			}				
		}		
		return result;
	}
	
	public static String authorized_request(Method actionMethod){
	    byte status = 1;    
		status = authenticated();
		if(status > AUTHENTICATED)	session_destroy(status);   
		return NOTIFICATION[status];	
	}
	
	 
		
}

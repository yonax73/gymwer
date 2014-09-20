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

	
	private final static String EXCLUDE_ACTIONS[] = {
			"login",
			"signUp",
			"createAccount",
			"signIn",
			"signOut"
	};	
	private final static int EXCLUDE_ACIONS_LENGHT = EXCLUDE_ACTIONS.length;	
	
	public static boolean filter_action(Method actionMethod){
		boolean result = true;
		if(actionMethod!=null){
			final String action = actionMethod.getName();
			if(action!=null && !action.isEmpty()){
				int i = 0;			
				do {
					if(action.equals(EXCLUDE_ACTIONS[i++]))result = false;					
				} while (i < EXCLUDE_ACIONS_LENGHT && result);
			}				
		}		
		return result;
	}
	
	public static String authorized_request(Method actionMethod){
	    byte status = 1;
        if(filter_action(actionMethod)){
    		status = authenticated();
    		if(status > AUTHENTICATED)	session_destroy(status);    		
        }        
		return NOTIFICATION[status];	
	}
	
		
}

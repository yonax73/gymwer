package com.yonaxtics.gymwer.sec.login.logic;

import com.yonaxtics.gymwer.sec.login.dao.LoginDao;
import com.yonaxtics.gymwer.sec.login.entity.Login;

/** 
 * Clase     : LoginLogic.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 4, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class LoginLogic {	
	
	public static boolean create(Login login) {		
		boolean result = false;					 
		if(login != null && !login.exists() && !login.isEmpty() ){		    		  
             result = LoginDao.create(login);	    	
		}	      
	    return result;
	}	
	
	public static boolean signIn(Login login, String nameGym){		
		boolean result = false;		
		if(login != null && !login.isEmpty() && nameGym != null && nameGym.isEmpty()){
			result = LoginDao.signIn(login,nameGym);
		}
		return result;		
	}
	
	public static boolean exists(Login login){		
		boolean result = false;		
		if(login != null &&  !login.isEmpty()){			
			result = LoginDao.exists(login);
		}		
		return result;
	}
}

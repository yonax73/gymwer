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
	
	public static boolean signIn(Login login){		
		boolean result = false;		
		if(login != null && !login.getPerson().exists() && !login.getPerson().getGym().getName().isEmpty() && 
		  !login.getPerson().getUser().getEmail().isEmpty() && 
		  !login.getPerson().getUser().getPassword().isEmpty()){			
			    result = LoginDao.signIn(login);			
		}		
		return result;		
	}	
}

package com.yonaxtics.gymwer.set.permission.controller;



import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.yonaxtics.gymwer.sec.securedController;
import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.set.permission.logic.PermissionLogic;

/** 
 * Class     : PermissionControl.java.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 15, 2014<br/> 
 * User      : YQ<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */
public class PermissionControl extends Controller {	
	
	public static Result loadNav(){		
		Login login = (Login) securedController.getAttribute(securedController.LOGIN);	
    	if(login!=null && PermissionLogic.loadNav(login.getPerson())){    	    
    		return ok(enc(Json.toJson(login.getPerson()).toString()));
    	}		
    	return ok("Internal Error 4001");		
	}
}

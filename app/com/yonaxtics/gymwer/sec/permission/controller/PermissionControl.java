package com.yonaxtics.gymwer.sec.permission.controller;



import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import play.libs.Json;
import play.mvc.Result;

import com.yonaxtics.gymwer.sec.SecuredController;
import com.yonaxtics.gymwer.sec.permission.logic.PermissionLogic;
import com.yonaxtics.gymwer.set.user.entity.User;

/** 
 * Class     : PermissionControl.java.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 15, 2014<br/> 
 * User      : YQ<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */
public class PermissionControl extends SecuredController {	
	
	public static Result loadNav(){		
		User user = user_loggedIn();
    	if(user!=null && PermissionLogic.load(user)){ 
    		user.getSerial();
    		return authenticated(enc(Json.toJson(user.getRole().getPermissionsLoad()).toString()));
    	}		
    	return ok("Error trying load the navegation!");		
	}
}

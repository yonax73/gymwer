package com.yonaxtics.gymwer.set.permission.controller;



import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.yonaxtics.gymwer.sec.session.Session;
import com.yonaxtics.gymwer.set.permission.logic.PermissionLogic;
import com.yonaxtics.gymwer.set.person.entity.Person;

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
		Person contact = (Person) Session.getAttribute(Session.OK);	
    	if(PermissionLogic.loadNav(contact)){    	    
    		return ok(enc(Json.toJson(contact).toString()));
    	}		
    	return ok("Internal Error 4001");		
	}
}

package com.yonaxtics.gymwer.set.permission.controller;

import static com.yonaxtics.gymwer.sec.Sec.dec;
import static com.yonaxtics.gymwer.sec.Sec.enc;
import static com.yonaxtics.gymwer.util.Constant.SESSION_GYM_NAME;
import static com.yonaxtics.gymwer.util.Constant.SESSION_OK;
import static com.yonaxtics.gymwer.util.Constant.SESSION_USER_NAME;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.set.permission.logic.PermissionLogic;
import com.yonaxtics.gymwer.set.person.entity.Person;
import com.yonaxtics.gymwer.set.user.entity.User;

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
		
		Person contact = new Person(Integer.parseInt(dec(session(SESSION_OK))));
		contact.setUser(new User(session(SESSION_USER_NAME)));
		contact.setGym(new Gym(session(SESSION_GYM_NAME)));
		
    	if(PermissionLogic.loadNav(contact)){
    	    
    		return ok(enc(Json.toJson(contact).toString()));
    	}		
		
    	return ok("Internal Error 4001");
		
	}
}

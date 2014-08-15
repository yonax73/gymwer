package com.yonaxtics.gymwer.set.permission.controller;

import static com.yonaxtics.gymwer.sec.Sec.enc;
import static com.yonaxtics.gymwer.util.Constant.SESSION_OK;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/** 
 * Class     : PermissionControl.java.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 15, 2014<br/> 
 * User      : YQ<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */
public class PermissionControl extends Controller {
   
	
	
	
	public static Result load(){
		
		Gym gym = new Gym((Integer.parseInt(session(SESSION_OK))));
    	gym.setName("Prueba");    	
    	return ok(enc(Json.toJson(gym).toString()));
		
	}
}

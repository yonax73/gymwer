package com.yonaxtics.gymwer.dpa.gym.controller;

import static com.yonaxtics.gymwer.sec.Sec.enc;
import static com.yonaxtics.gymwer.util.Constant.SESSION_OK;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.dpa.home.home;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (9/8/2014)
 *
 */
public class GymControl extends Controller {

	
	public static Result home(){
		
				
	    if(session(SESSION_OK)!= null && Integer.parseInt(session(SESSION_OK)) > 0) {	    	
	    	
	    	return ok(home.render());
	    	
	    } else {
	    	
	        return unauthorized("Oops, you are not connected");
	    }		
		
	}
	
	
	
	public static Result load(){
		
		Gym gym = new Gym((Integer.parseInt(session(SESSION_OK))));
    	gym.setName("Prueba");    	
    	return ok(enc(Json.toJson(gym).toString()));
		
	}
	
	
	
}

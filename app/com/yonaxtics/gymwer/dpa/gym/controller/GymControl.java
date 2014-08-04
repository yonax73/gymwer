package com.yonaxtics.gymwer.dpa.gym.controller;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.dpa.home.home;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.util.Constant;

public class GymControl extends Controller {

	
	public static Result home(){
		
		int gymId = Integer.parseInt(session(Constant.SESSION_OK));
		
	    if(gymId > 0) {	    	
	    	
	    	return ok(home.render());
	    	
	    } else {
	    	
	        return unauthorized("Oops, you are not connected");
	    }		
		
	}
	
	
	
	public static Result load(){
		
		Gym gym = new Gym(Integer.parseInt(session(Constant.SESSION_OK)));
    	gym.setName("Prueba");
    	
    	return ok(Json.toJson(gym));
		
	}
	
	
	
}

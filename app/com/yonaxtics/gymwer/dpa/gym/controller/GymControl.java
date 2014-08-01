package com.yonaxtics.gymwer.dpa.gym.controller;

import com.yonaxtics.gymwer.util.Constant;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.dpa.dashboard.dashboard;

public class GymControl extends Controller {

	
	public static Result home(){
		
		String user = session(Constant.SESSION_OK);
	    if(user != null) {
	    	
	    	return ok(dashboard.render());
	    	
	    } else {
	    	
	        return unauthorized("Oops, you are not connected");
	    }
		
		
	}
	
	
}

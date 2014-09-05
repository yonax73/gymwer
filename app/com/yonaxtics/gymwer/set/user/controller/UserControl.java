package com.yonaxtics.gymwer.set.user.controller;


import play.mvc.Controller;
import play.mvc.Result;
import views.html.dpa.user.user;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class UserControl extends Controller {		
	
	public static Result users(){		
		return ok(user.render());		
	}	

}

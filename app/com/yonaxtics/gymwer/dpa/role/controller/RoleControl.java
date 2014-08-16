package com.yonaxtics.gymwer.dpa.role.controller;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.dpa.role.role;

/** 
 * Clase     : RoleControl.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 16, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class RoleControl extends Controller {

	
	
	
	public static Result roles(){
		
		return ok(role.render());
	}
}

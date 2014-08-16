package com.yonaxtics.gymwer.set.person.controller;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.set.person.profile;

/** 
 * Clase     : PersonControl.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 16, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class PersonControl extends Controller {

	
	
	public static Result profile(){
		
		return ok(profile.render());
	}
	
}

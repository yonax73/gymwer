package com.yonaxtics.gymwer.dpa.client.controller;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.dpa.client.client;

/** 
 * Clase     : Client.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 15, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class ClientControl extends Controller {

	
	
	
	public static Result clients(){
		
		return ok(client.render());
	}
}

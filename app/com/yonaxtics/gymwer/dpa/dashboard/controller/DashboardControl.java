package com.yonaxtics.gymwer.dpa.dashboard.controller;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.dpa.dashboard.dashboard;

import com.yonaxtics.gymwer.sec.SecuredController;

/** 
 * Clase     : DashboardControl.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 4, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class DashboardControl extends Controller {
	
	public static Result dashboard(){	
//	    if(securedController.exists(securedController.LOGIN)) {	    	 
//    	   	return ok(dashboard.render());
//	    } else {
//	    	securedController.sessionDestroy();	
//			return redirect("/login");
//	    }		
		return ok();
	}	
}

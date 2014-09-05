package com.yonaxtics.gymwer.dpa.dashboard.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.util.Constant.SESSION_OK;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.dpa.dashboard.dashboard;

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
	    if(session(SESSION_OK)!= null && Integer.parseInt(dec(session(SESSION_OK))) > 0) {	    	 
    	   	return ok(dashboard.render());
	    } else {
	    	session().clear();			
			return redirect("/login");
	    }		
	}	
}

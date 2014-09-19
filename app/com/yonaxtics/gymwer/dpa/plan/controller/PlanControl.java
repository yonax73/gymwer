package com.yonaxtics.gymwer.dpa.plan.controller;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.dpa.plan.*;

/** 
 * Clase     : PlanControl.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 16, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class PlanControl extends Controller {	
	
	public static Result plans(){		
		return ok(masterPlan.render());
	}
	
	public static Result plan(){
		return ok(plan.render());
	}
}

package com.yonaxtics.gymwer.dpa.plan.controller;

import play.mvc.Result;
import views.html.dpa.plan.plans;
import views.html.dpa.plan.plan;

import com.yonaxtics.gymwer.sec.SecuredController;

/** 
 * Clase     : PlanControl.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 16, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class PlanControl extends SecuredController {	
	
	public static Result plans(){	
	      return authenticated(plans.render());	
	}
	
	public static Result plan(){
		return authenticated(plan.render());
	}
}

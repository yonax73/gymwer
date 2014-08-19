	package com.yonaxtics.gymwer.dpa.gym.controller;

import static com.yonaxtics.gymwer.sec.Sec.enc;
import static com.yonaxtics.gymwer.sec.Sec.dec;
import static com.yonaxtics.gymwer.util.Constant.SESSION_OK;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.set.gym.*;

import com.yonaxtics.gymwer.set.person.entity.Person;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (9/8/2014)
 *
 */
public class GymControl extends Controller {

	
	public static Result dashboard(){		
				
	    if(session(SESSION_OK)!= null && Integer.parseInt(dec(session(SESSION_OK))) > 0) {	    	
	    	
	    	return ok(dashboard.render());
	    	
	    } else {
	    	
	    	session().clear();			
			return redirect("/login");
	    }		
		
	}
	
	
	
	public static Result gym(){
		
		return ok(gym.render());
	}
	
	
	
	public static Result load(){			
			
			Person contact = new Person((Integer.parseInt(dec(session(SESSION_OK)))));
			contact.setName("Prueba");    	
	    	return ok(enc(Json.toJson(contact).toString()));	
		
	}
	
	
	
}

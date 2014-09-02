package com.yonaxtics.gymwer.set.person.controller;

import static com.yonaxtics.gymwer.sec.Sec.dec;
import static com.yonaxtics.gymwer.sec.Sec.enc;
import static com.yonaxtics.gymwer.util.Constant.SESSION_OK;

import java.util.Map;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.set.person.profile;

import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.set.master.entity.Address;
import com.yonaxtics.gymwer.set.master.entity.Phone;
import com.yonaxtics.gymwer.set.person.entity.Person;
import com.yonaxtics.gymwer.set.person.logic.PersonLogic;
import com.yonaxtics.gymwer.set.user.entity.User;

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
	    if(session(SESSION_OK)!= null && Integer.parseInt(dec(session(SESSION_OK))) > 0) {	    	
	    	return ok(profile.render());	    	
	    } else {	    	
	    	session().clear();			
			return redirect("/login");
	    }		
	}
	
	
	
	public static Result loadProfile(){		
		Person contact = new  Person(Integer.parseInt(dec(session(SESSION_OK))));		
		if(PersonLogic.loadProfile(contact)){						
			return ok(enc(Json.toJson(contact).toString()));			
		} else {		     
			return ok("Internal Error 2002");			
		}		
	}
	
	public static Result saveProfile(){
		String result = null;
		User user = null;
		Location location = null;
		Phone phone = null;
		Address adress = null;
		Person person = null;
		final Map<String, String[]>data = request().body().asFormUrlEncoded();
		user = new User(Integer.parseInt(data.get("?")[8]), data.get("?")[0]);
		user.setEmail(data.get("?")[1]);
		return ok();
	}
	
	
		
}

package com.yonaxtics.gymwer.set.profile.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import static com.yonaxtics.gymwer.util.Constant.REQUEST_SUCCESS;

import java.util.Map;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.set.profile.profile;

import com.yonaxtics.gymwer.sec.session.Session;
import com.yonaxtics.gymwer.set.location.logic.LocationLogic;
import com.yonaxtics.gymwer.set.master.logic.MasterLogic;
import com.yonaxtics.gymwer.set.person.entity.Person;
import com.yonaxtics.gymwer.set.person.logic.PersonLogic;
import com.yonaxtics.gymwer.set.profile.logic.ProfileLogic;
import com.yonaxtics.gymwer.set.user.logic.UserLogic;

/** 
 * Clase     : ProfileControl.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 4, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class ProfileControl extends Controller {
	
	public static Result profile(){		
	    if(Session.exists(Session.OK)) {	    	
	    	return ok(profile.render());	    	
	    } else {	    	
	    	Session.end();			
			return redirect("/login");
	    }		
	}	
	
	public static Result load(){		
		Person contact =(Person) Session.getAttribute(Session.OK);		
		if(ProfileLogic.load(contact)){			
			Session.setAttribute(Session.OK, contact);
			return ok(enc(Json.toJson(contact).toString()));			
		} else {		     
			return ok("Internal Error 9001");			
		}		
	}
	
	public static Result save(){
		String result = null;
		Person contact =(Person) Session.getAttribute(Session.OK);
		final Map<String, String[]>data = request().body().asFormUrlEncoded();		
		contact.getUser().setName(dec(data.get("txtNameUser")[0]));
		contact.getUser().getDefaultAction().setId(Integer.parseInt(dec(data.get("txtDefaultActionId")[0])));
		if(UserLogic.update(contact.getUser())){			
			contact.getLocation().getPhone().setPhone(dec(data.get("txtPhone")[0]));
			if(MasterLogic.save(contact.getLocation().getPhone())){				
				contact.getLocation().getAddress().setAddress(dec(data.get("txtAddress")[0]));
			    if(MasterLogic.save(contact.getLocation().getAddress())){
			    	if(LocationLogic.save(contact.getLocation())){			    		
			    		contact.setDocument(dec(data.get("txtDocument")[0]));
			    		contact.setName((dec(data.get("txtFullName")[0])));			    		
			    		if(PersonLogic.update(contact)){
			    			return ok(REQUEST_SUCCESS);		
			    		}else{
			    			result = "Internal Error 2002";
			    		}				    				    	
			    	}else{
			    	    result = "Internal Error 8001";	
			    	}	
			    }else{
			    	result = "Internal Error 7002";	
			    }
			}else{
				result = "Internal Error 7001";
			}			
		}else{
			result = "Internal Error 1002";
		}
		return ok(result);
	}
}

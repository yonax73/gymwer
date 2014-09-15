package com.yonaxtics.gymwer.set.profile.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import static com.yonaxtics.gymwer.util.Constant.REQUEST_SUCCESS;

import java.util.Map;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.set.profile.profile;

import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.sec.session.Session;
import com.yonaxtics.gymwer.set.location.logic.LocationLogic;
import com.yonaxtics.gymwer.set.master.logic.MasterLogic;
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
	    if(Session.exists(Session.LOGIN)) {	    	
	    	return ok(profile.render());	    	
	    } else {	    	
	    	Session.clear();			
			return redirect("/login");
	    }		
	}	
	
	public static Result load(){		
		Login login = (Login) Session.getAttribute(Session.LOGIN);		
		if(ProfileLogic.load(login.getPerson())){			
			Session.setAttribute(Session.LOGIN, login);
			return ok(enc(Json.toJson(login.getPerson()).toString()));			
		} else {		     
			return ok("Internal Error 9001");			
		}		
	}
	
	public static Result save(){
		String result = null;
		Login login = (Login) Session.getAttribute(Session.LOGIN);
		final Map<String, String[]>data = request().body().asFormUrlEncoded();		
		login.getPerson().getUser().setName(dec(data.get("txtNameUser")[0]));
		login.getPerson().getUser().getDefaultAction().setId(Integer.parseInt(dec(data.get("txtDefaultActionId")[0])));
		if(UserLogic.update(login.getPerson().getUser())){			
			login.getPerson().getLocation().getPhone().setPhone(dec(data.get("txtPhone")[0]));
			if(MasterLogic.save(login.getPerson().getLocation().getPhone())){				
				login.getPerson().getLocation().getAddress().setAddress(dec(data.get("txtAddress")[0]));
			    if(MasterLogic.save(login.getPerson().getLocation().getAddress())){
			    	if(LocationLogic.save(login.getPerson().getLocation())){			    		
			    		login.getPerson().setDocument(dec(data.get("txtDocument")[0]));
			    		login.getPerson().setName((dec(data.get("txtFullName")[0])));			    		
			    		if(PersonLogic.update(login.getPerson())){
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

package com.yonaxtics.gymwer.set.person.controller;

import static com.yonaxtics.gymwer.sec.Sec.dec;
import static com.yonaxtics.gymwer.sec.Sec.enc;
import static com.yonaxtics.gymwer.util.Constant.REQUEST_SUCCESS;
import static com.yonaxtics.gymwer.util.Constant.SESSION_OK;

import java.util.Map;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.set.person.profile;

import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.set.location.logic.LocationLogic;
import com.yonaxtics.gymwer.set.master.entity.Address;
import com.yonaxtics.gymwer.set.master.entity.Phone;
import com.yonaxtics.gymwer.set.master.logic.MasterLogic;
import com.yonaxtics.gymwer.set.person.entity.Person;
import com.yonaxtics.gymwer.set.person.logic.PersonLogic;
import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.set.user.logic.UserLogic;

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
		Address address = null;
		Person contact = null;
		final Map<String, String[]>data = request().body().asFormUrlEncoded();
		user = new User(Integer.parseInt(dec(data.get("txtUserId")[0])), dec(data.get("txtNameUser")[0]));
		user.setEmail(dec(data.get("txtEmail")[0]));
		user.setRole(new Role(Integer.parseInt(dec(data.get("txtRoleId")[0]))));
		user.setDefaultAction(new Action(Integer.parseInt(dec(data.get("txtDefaultActionId")[0]))));
		if(UserLogic.update(user)){
			phone = new Phone(Integer.parseInt(dec(data.get("txtPhoneId")[0])),dec(data.get("txtPhone")[0]));						
			if(MasterLogic.save(phone) ){
				address = new Address(Integer.parseInt(dec(data.get("txtAddressId")[0])),dec(data.get("txtAddress")[0]));
			    if(MasterLogic.save(address)){
			    	location = new Location(Integer.parseInt(dec(data.get("txtLocationId")[0])));
			    	location.setPhone(phone);
			    	location.setAddress(address);
			    	if((location.getPhone() != null && location.getPhone().getId() >0) || (location.getAddress() !=null && location.getAddress().getId()>0)){
				    	LocationLogic.save(location);				    	
			    	}
		    		contact = new Person(Integer.parseInt(dec(session(SESSION_OK))));
		    		contact.setDocument(dec(data.get("txtDocument")[0]));
		    		contact.setName((dec(data.get("txtFullName")[0])));
		    		contact.setLocation(location);
		    		contact.setUser(user);
		    		if(PersonLogic.update(contact)){
		    			return ok(REQUEST_SUCCESS);		
		    		}else{
		    			result = "Internal Error 2003";
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

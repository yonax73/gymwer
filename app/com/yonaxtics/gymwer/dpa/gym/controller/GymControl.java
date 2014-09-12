	package com.yonaxtics.gymwer.dpa.gym.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import static com.yonaxtics.gymwer.util.Constant.REQUEST_SUCCESS;

import java.util.Map;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.dpa.gym.gym;

import com.yonaxtics.gymwer.dpa.gym.logic.GymLogic;
import com.yonaxtics.gymwer.sec.session.Session;
import com.yonaxtics.gymwer.set.location.logic.LocationLogic;
import com.yonaxtics.gymwer.set.master.logic.MasterLogic;
import com.yonaxtics.gymwer.set.person.entity.Person;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (9/8/2014)
 *
 */
public class GymControl extends Controller {
	
	public static Result gym(){
		if(Session.exists(Session.OK)) {
		  return ok(gym.render());
		}else{
	    	Session.end();			
			return redirect("/login");			
		}
	}	
	
	public static Result load(){		
		Person contact = (Person) Session.getAttribute(Session.OK);
		if(GymLogic.load(contact.getGym())){
			Session.setAttribute(Session.OK, contact);
			return ok(enc(Json.toJson(contact.getGym()).toString()));	
		}else{
			return ok("Internal Error 3002");
		}		
	}
	
	public static Result save(){
		String result = null;
		Person contact = (Person) Session.getAttribute(Session.OK);
		final Map<String,String[]>data = request().body().asFormUrlEncoded();
		contact.getGym().setName(dec(data.get("txtName")[0]));		
	    if(!contact.getGym().getName().isEmpty()){	    	
	    	contact.getGym().getLocation().getPhone().setPhone((dec(data.get("txtPhone")[0])));			
			if(MasterLogic.save(contact.getGym().getLocation().getPhone()) ){
				contact.getGym().getLocation().getAddress().setAddress(dec(data.get("txtAddress")[0]));				
			    if(MasterLogic.save(contact.getGym().getLocation().getAddress())){			    	
			    	if(LocationLogic.save(contact.getGym().getLocation())){
			    	    contact.getGym().setName(dec(data.get("txtNit")[0]));			    	   
			    	   if(GymLogic.update(contact.getGym())){
			    			return ok(REQUEST_SUCCESS);		
			    	   }else{
			    			result = "Internal Error 3003";
			    		}				    				    	
			    	}else{
			    	    result = "Internal Error 8002";	
			    	}	
			    }else{
			    	result = "Internal Error 7004";	
			    }		
		  }else{
			  result = "Internal Error 7003";
		  }		   
	    }else {
	    	result = "The user Name is required and can't empty";
	    }	
		return ok(result);
	}
	
	
	
}

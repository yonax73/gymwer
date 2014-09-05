	package com.yonaxtics.gymwer.dpa.gym.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import static com.yonaxtics.gymwer.util.Constant.REQUEST_SUCCESS;
import static com.yonaxtics.gymwer.util.Constant.SESSION_GYM_ID;
import static com.yonaxtics.gymwer.util.Constant.SESSION_OK;

import java.util.Map;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.dpa.gym.gym;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.dpa.gym.logic.GymLogic;
import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.set.location.logic.LocationLogic;
import com.yonaxtics.gymwer.set.master.entity.Address;
import com.yonaxtics.gymwer.set.master.entity.Phone;
import com.yonaxtics.gymwer.set.master.logic.MasterLogic;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (9/8/2014)
 *
 */
public class GymControl extends Controller {
	
	public static Result gym(){
		if(session(SESSION_OK)!= null && Integer.parseInt(dec(session(SESSION_OK))) > 0) {
		  return ok(gym.render());
		}else{
	    	session().clear();			
			return redirect("/login");			
		}
	}	
	
	public static Result load(){			
		Gym gym = new Gym(Integer.parseInt(dec(session(SESSION_GYM_ID))));
		if(GymLogic.load(gym)){
			return ok(enc(Json.toJson(gym).toString()));	
		}else{
			return ok("Internal Error 3002");
		}		
	}
	
	public static Result save(){
		String result = null;
		Location location = null;
		Phone phone = null;
		Address address = null;
		Gym gym = null;
		final Map<String,String[]>data = request().body().asFormUrlEncoded();
		gym = new Gym(dec(data.get("txtName")[0]));		
	    if(!gym.getName().isEmpty()){
			phone = new Phone(Integer.parseInt(dec(data.get("txtPhoneId")[0])),dec(data.get("txtPhone")[0]));						
			if(MasterLogic.save(phone) ){
				address = new Address(Integer.parseInt(dec(data.get("txtAddressId")[0])),dec(data.get("txtAddress")[0]));
			    if(MasterLogic.save(address)){
			    	location = new Location(Integer.parseInt(dec(data.get("txtLocationId")[0])));
			    	location.setPhone(phone);
			    	location.setAddress(address);
			    	if(LocationLogic.save(location)){
				       gym.setId(Integer.parseInt(dec(session(SESSION_GYM_ID))));
				       gym.setNit(dec(data.get("txtNit")[0]));
			    	   gym.setLocation(location);  
			    	   if(GymLogic.update(gym)){
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

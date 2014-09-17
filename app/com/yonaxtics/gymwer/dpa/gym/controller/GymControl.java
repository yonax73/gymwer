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
import com.yonaxtics.gymwer.sec.securedController;
import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.set.location.logic.LocationLogic;
import com.yonaxtics.gymwer.set.master.logic.MasterLogic;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (9/8/2014)
 *
 */
public class GymControl extends Controller {
	
	public static Result gym(){
		if(securedController.exists(securedController.LOGIN)) {
		  return ok(gym.render());
		}else{
	    	securedController.sessionClear();			
			return redirect("/login");			
		}
	}	
	
	public static Result load(){		
		Login login = (Login) securedController.getAttribute(securedController.LOGIN);
		if(GymLogic.load(login.getPerson().getGym())){
			securedController.setAttribute(securedController.LOGIN, login);
			return ok(enc(Json.toJson(login.getPerson().getGym()).toString()));	
		}else{
			return ok("Internal Error 3002");
		}		
	}
	
	public static Result save(){
		String result = null;
		Login login = (Login)  securedController.getAttribute(securedController.LOGIN);
		final Map<String,String[]>data = request().body().asFormUrlEncoded();
		login.getPerson().getGym().setName(dec(data.get("txtName")[0]));		
	    if(!login.getPerson().getGym().getName().isEmpty()){	    	
	    	login.getPerson().getGym().getLocation().getPhone().setPhone((dec(data.get("txtPhone")[0])));			
			if(MasterLogic.save(login.getPerson().getGym().getLocation().getPhone()) ){
				login.getPerson().getGym().getLocation().getAddress().setAddress(dec(data.get("txtAddress")[0]));				
			    if(MasterLogic.save(login.getPerson().getGym().getLocation().getAddress())){			    	
			    	if(LocationLogic.save(login.getPerson().getGym().getLocation())){
			    	    login.getPerson().getGym().setName(dec(data.get("txtNit")[0]));			    	   
			    	   if(GymLogic.update(login.getPerson().getGym())){
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

package com.yonaxtics.gymwer.sec.login.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;

import java.util.Map;

import play.mvc.Result;
import views.html.sec.login.login;
import views.html.sec.login.signup;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.dpa.gym.logic.GymLogic;
import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.sec.securedController;
import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.sec.login.logic.LoginLogic;
import com.yonaxtics.gymwer.set.person.entity.Person;
import com.yonaxtics.gymwer.set.person.logic.PersonLogic;
import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.set.user.logic.UserLogic;

/** 
 * Class     : LoginControl.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 4, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class LoginControl extends securedController {	

	public static  Result  login() {
        if(isAuthenticated()){
        	User user = getUser();
        	if(user!=null){
        		return redirect(user.getDefaultAction().getUrl());	
        	}
        }			
		return ok(login.render());   
	}	
	
	public static Result signUp() {		 
		return ok(signup.render());   
	}	
	
	public static Result signOut(){
		sessionClear();
		return redirect("/login");
	}	
	
	public static Result createAccount() {
		String result = null;	
		Login login = null;
		final Map<String, String[]> data = request().body().asFormUrlEncoded();		
		if (dec(data.get("cbxTerms")[0]).equals(CHECKED)) {
			if (data.get("txtPassword")[0].equals(data.get("txtPasswordConfirm")[0])) {		
				login =new Login(dec(data.get("txtEmail")[0]), data.get("txtPassword")[0],new User(new Role(Role.SUPER_ADMIN)));				
				if(!LoginLogic.exists(login)){												
			        if(LoginLogic.create(login)){//hacer el user create			        	
			        	gym = new Gym(dec(data.get("txtNameGym")[0]));		        		
		        		if(GymLogic.create(gym)){			        	
		        			contact = new Person(user,gym);			        			
			        	if(PersonLogic.create(contact)){				        			
			        			return ok(REQUEST_SUCCESS);			        			
			        		}else {			        		   
			        			result = "Internal Error 2001";
			        		}			        		
			        	}else {			        		
			        		result = "Internal Error 3001";
			        	}			        	
			        } else {			        	
			        	result = "Internal Error 1001";
			        }						
				} else {					
					result = "This user already exists";					
				}					
			} else { 				
				result = "The password and its confirm are not the same."; 
		 	}			
		} else {			
			result = "Please choose Terms and Conditions."; 
		}
		return ok(result);
	}	
	
	public static Result signIn(){		
		final Map<String, String[]> data = request().body().asFormUrlEncoded();	request();	
		Login login = new Login(new Person(new User(dec(data.get("txtEmail")[0]),data.get("txtPassword")[0]), new Gym(dec(data.get("txtSiteName")[0]))));
		if(LoginLogic.signIn(login)){
			     initSession();
				//show the message after the signIn
				return ok(login.getPerson().getUser().getDefaultAction().getUrl());				
			} else {				
				return ok(REQUEST_BAD);
		 }							
	}	
}

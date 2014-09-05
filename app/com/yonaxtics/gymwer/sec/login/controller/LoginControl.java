package com.yonaxtics.gymwer.sec.login.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import static com.yonaxtics.gymwer.util.Constant.CHECKED;
import static com.yonaxtics.gymwer.util.Constant.MASTER_VALUE_ROL_SUPER_ADMIN;
import static com.yonaxtics.gymwer.util.Constant.REQUEST_BAD;
import static com.yonaxtics.gymwer.util.Constant.REQUEST_SUCCESS;
import static com.yonaxtics.gymwer.util.Constant.SESSION_DEFAULT_ACTION_URL;
import static com.yonaxtics.gymwer.util.Constant.SESSION_GYM_ID;
import static com.yonaxtics.gymwer.util.Constant.SESSION_GYM_NAME;
import static com.yonaxtics.gymwer.util.Constant.SESSION_OK;
import static com.yonaxtics.gymwer.util.Constant.SESSION_USER_NAME;
import static com.yonaxtics.gymwer.util.Constant.SESSION_USER_EMAIL;

import java.util.Map;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.dpa.gym.logic.GymLogic;
import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.set.person.entity.Person;
import com.yonaxtics.gymwer.set.person.logic.PersonLogic;
import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.set.user.logic.UserLogic;
import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.sec.login.logic.LoginLogic;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.sec.login.login;
import views.html.sec.login.signup;

/** 
 * Clase     : LoginControl.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 4, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class LoginControl extends Controller {
	
	

	public static  Result  login() {
		if(session(SESSION_OK)!= null){					
			return redirect(SESSION_DEFAULT_ACTION_URL);
		}		
		return ok(login.render());   
	}	
	
	public static Result signUp() {
		return ok(signup.render());   
	}	
	
	public static Result signOut(){		
		session().clear();		
		return redirect("/login");
	}	
	
	public static Result createAccount() {
		String result = null;
		User user = null;
		Person contact = null;
		Gym gym = null;				
		final Map<String, String[]> data = request().body().asFormUrlEncoded();		
		if (dec(data.get("cbxTerms")[0]).equals(CHECKED)) {
			if (data.get("txtPassword")[0].equals(data.get("txtPasswordConfirm")[0])) {				
				user = new User(dec(data.get("txtEmail")[0]), data.get("txtPassword")[0], new Role(MASTER_VALUE_ROL_SUPER_ADMIN));
				if(!UserLogic.exists(user)){												
			        if(UserLogic.create(user)){			        	
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
		final Map<String, String[]> data = request().body().asFormUrlEncoded();			
		Person contact = new Person(new User(dec(data.get("txtEmail")[0]),data.get("txtPassword")[0]), new Gym(dec(data.get("txtSiteName")[0])));		
		if(LoginLogic.signIn(new Login(contact))){				
				session(SESSION_OK, enc(String.valueOf(contact.getId())));
				session(SESSION_USER_NAME,contact.getUser().getName());
				session(SESSION_GYM_NAME,contact.getGym().getName());
				session(SESSION_DEFAULT_ACTION_URL,contact.getUser().getDefaultAction().getUrl());		
				session(SESSION_GYM_ID,enc(String.valueOf(contact.getGym().getId())));	
				session(SESSION_USER_EMAIL,contact.getUser().getEmail());	
				return ok(contact.getUser().getDefaultAction().getUrl());				
			} else {				
				return ok(REQUEST_BAD);
		 }							
	}	
}

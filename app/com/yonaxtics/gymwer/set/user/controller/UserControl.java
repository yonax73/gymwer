package com.yonaxtics.gymwer.set.user.controller;

import java.util.Map;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.set.login.login;
import views.html.set.login.signup;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.dpa.gym.logic.GymLogic;
import com.yonaxtics.gymwer.set.master.entity.Role;
import com.yonaxtics.gymwer.set.person.entity.Person;
import com.yonaxtics.gymwer.set.person.logic.PersonLogic;
import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.set.user.logic.UserLogic;
import static com.yonaxtics.gymwer.util.Constant.*;

import com.yonaxtics.security.cryptography.aes.*;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class UserControl extends Controller {

	
	
	
	public static  Result  login() {

		
		
		return ok(login.render());   
	}

	
	
	
	public static Result signUp() {

		return ok(signup.render());   
	}

	
	
	
	public static Result createAccount() {

		String result = null;
		User user = null;
		Person contact = null;
		Gym gym = null;		
		
		final Map<String, String[]> data = request().body().asFormUrlEncoded();		
		
		if (data.get("cbxTerms")[0].equals(CHECKED)) {

			if (data.get("txtPassword")[0].equals(data.get("txtConfirmPassword")[0])) {

				contact = new Person(data.get("txtEmail")[0]);

				if(!PersonLogic.exists(contact)){
				
					user = new User(USER_ADMIN,data.get("txtPassword")[0]);				
					
			        if(UserLogic.create(user)){
			        	
			        	contact.setUser(user);
			        	contact.setRole(new Role(ROL_ADMIN));
			        	
			        	if(PersonLogic.create(contact)){
			        		
			        		gym = new Gym(data.get("txtName")[0], contact);
			        		
			        		if(GymLogic.create(gym)){
			        			
			        			return ok(REQUEST_SUCCESS);
			        			
			        		}else {
			        		   
			        			result = "3001 - Server Internal Error";
			        		}		        		
			        		
			        	}else {
			        		
			        		result = "2001 - Server Internal Error";
			        	}
			        	
			        } else {
			        	
			        	result = "1001 - Server Internal Error";
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
		
		String result = null;
		User user = new User(data.get("txtPassword")[0]);
		Person  contact = new Person(data.get("txtEmail")[0], user);
		Gym gym = new Gym(data.get("txtName")[0], contact);				
	

		if(GymLogic.signIn(gym)){
			
			session(SESSION_OK, String.valueOf(gym.getId()));			
			return ok(REQUEST_SUCCESS);
			
		} else {
			
			result = "The name, password or user are incorrect!";
		}        
		
		return ok(result);
		
	}
}

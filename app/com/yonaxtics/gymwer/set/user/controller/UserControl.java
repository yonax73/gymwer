package com.yonaxtics.gymwer.set.user.controller;


import static com.yonaxtics.gymwer.sec.Sec.dec;
import static com.yonaxtics.gymwer.util.Constant.CHECKED;
import static com.yonaxtics.gymwer.util.Constant.REQUEST_SUCCESS;
import static com.yonaxtics.gymwer.util.Constant.ROL_SUPER_ADMIN;
import static com.yonaxtics.gymwer.util.Constant.SESSION_OK;


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
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class UserControl extends Controller {

	
	
	
	public static  Result  login() {

		if(session(SESSION_OK)!= null){
			
			session().remove(SESSION_OK);
		}
		
		return ok(login.render());   
	}

	
	
	
	public static Result signUp() {

		return ok(signup.render());   
	}
	
	
	
	public static Result signOut(){
		
		session().remove(SESSION_OK);
		
		return redirect("/login");
	}

	
	
	
	public static Result createAccount() {

		String result = null;
		User user = null;
		Person contact = null;
		Gym gym = null;		
		
		final Map<String, String[]> data = request().body().asFormUrlEncoded();		
		
		if (dec(data.get("?")[4]).equals(CHECKED)) {

			if (data.get("?")[2].equals(data.get("?")[3])) {
				
				user = new User(dec(data.get("?")[1]), data.get("?")[2], new Role(ROL_SUPER_ADMIN));

				if(!UserLogic.exists(user)){							
					
			        if(UserLogic.create(user)){
			        	
			        	gym = new Gym(dec(data.get("?")[0]));			        		
		        		
		        		if(GymLogic.create(gym)){
			        	
		        			contact = new Person(user,gym);	
		        			
			        	if(PersonLogic.create(contact)){			        	
			        			
			        			return ok(REQUEST_SUCCESS);
			        			
			        		}else {
			        		   
			        			result = "2001 - Server Internal Error";
			        		}		        		
			        		
			        	}else {
			        		
			        		result = "3001 - Server Internal Error";
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
		Person contact = new Person(new User(dec(data.get("?")[1]),data.get("?")[2]), new Gym(dec(data.get("?")[0])));			
		
		if(UserLogic.signIn(contact)){
				
				session(SESSION_OK, String.valueOf(contact.getId()));
				
				return ok(REQUEST_SUCCESS);
				
			} else {
				
				result = "The name, password or user are incorrect!!!";
		 }			
		
		return ok(result);
		
	}
}

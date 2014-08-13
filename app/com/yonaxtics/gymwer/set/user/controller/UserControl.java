package com.yonaxtics.gymwer.set.user.controller;


import static com.yonaxtics.gymwer.sec.Sec.dec;
import static com.yonaxtics.gymwer.util.Constant.CHECKED;
import static com.yonaxtics.gymwer.util.Constant.REQUEST_SUCCESS;
import static com.yonaxtics.gymwer.util.Constant.ROL_ADMIN;
import static com.yonaxtics.gymwer.util.Constant.SESSION_OK;
import static com.yonaxtics.gymwer.util.Constant.USER_ADMIN;

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

				contact = new Person(dec(data.get("?")[1]));

				if(!PersonLogic.exists(contact)){
				
					user = new User(USER_ADMIN,data.get("?")[2]);				
					
			        if(UserLogic.create(user)){
			        	
			        	contact.setUser(user);
			        	contact.setRole(new Role(ROL_ADMIN));
			        	
			        	if(PersonLogic.create(contact)){
			        		
			        		gym = new Gym(dec(data.get("?")[0]), contact);
			        		data.clear();
			        		
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
		User user = new User(data.get("?")[2]);
		Person  contact = new Person(dec(data.get("?")[1]), user);
		Gym gym = new Gym(dec(data.get("?")[0]), contact);			
			
			if(GymLogic.signIn(gym)){
				
				session(SESSION_OK, String.valueOf(gym.getId()));
					
				return ok(REQUEST_SUCCESS);
				
			} else {
				
				result = "The name, password or user are incorrect!!!";
			}			
		
		
		return ok(result);
		
	}
}

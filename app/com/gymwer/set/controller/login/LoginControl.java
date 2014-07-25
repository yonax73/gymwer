package com.gymwer.set.controller.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;



import com.fasterxml.jackson.databind.JsonNode;
import com.gymwer.util.Constant;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.set.login.*;



public class LoginControl extends Controller {

	  
	
	   public static Result login(){
		   
	        return ok(login.render());
	   }
	   
	   
	   
	   public static Result signUp(){
		   
		   return ok(signup.render());
	   }
	   
	   
	   
	   public static Result createAccount(){	
		   
		   final Map<Object, String[]> data = request().body().asFormUrlEncoded();
		   User user = null;
		   if(data.get("cbxTerms")[0] == Constant.CHECKED){
			   
			   if(data.get("txtPassword")[0] == data.get("txtConfirmPassword")[0]){
				   
				  user = new User(0,data.get("txtName")[0],data.get("txtPassword")[0]);
			   }
			   
			
		   }
		   
		   return ok(LoginControl.createAccount(request().body().asFormUrlEncoded()));		   
		   
	   }
	   
	   
	   

	   

	   
	   
}

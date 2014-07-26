package com.yonaxtics.gymwer.set.user.controller;

import java.util.Map;


import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.util.Constant;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.set.login.login;
import views.html.set.login.signup;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class UserControl extends Controller {

	public static Result login() {

		return ok(login.render());
	}

	
	
	
	public static Result signUp() {

		return ok(signup.render());
	}

	
	
	
	public static Result createAccount() {

		final Map<String, String[]> data = request().body().asFormUrlEncoded();
		
		User user = null;
		
		if (data.get("cbxTerms")[0] == Constant.CHECKED) {

			if (data.get("txtPassword")[0] == data.get("txtConfirmPassword")[0]) {

				user = new User(0, data.get("txtName")[0],data.get("txtPassword")[0]);
				
			}

		}

		return ok();

	}

}

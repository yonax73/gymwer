package com.yonaxtics.gymwer.sec.login.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;

import java.util.Map;

import play.mvc.Result;
import views.html.sec.login.login;
import views.html.sec.login.signup;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.dpa.gym.logic.GymLogic;
import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.sec.SecuredController;
import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.sec.login.logic.LoginLogic;
import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.set.user.logic.UserLogic;
import com.yonaxtics.gymwer.util.Utils;


/**
 * Class : LoginControl.java<br/>
 * Copyright : (c) 2014<br/>
 * Company : yonaxtics<br/>
 * date : Sep 4, 2014<br/>
 * User : yonatan<br/>
 * 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class LoginControl extends SecuredController {

	public static Result login() {
	       return authenticatedLogin(login.render());
	}

	public static Result signUp() {
		return ok(signup.render());
	}

	public static Result signOut() {
	     return sign_out();
	}

	/**
	 * Solo se permite crear un super admin por gimnasio, en caso de que exista
	 * el gimansio y el usuario, o solo el gimnasio no se podra crear la cuenta.
	 * 
	 * @return result
	 */
	public static Result createAccount() {
		String result = null;
		User user = null;
		final Map<String, String> data = Utils.deserializeJson(dec(request().body().asText()));
		if (data.get("cbxTerms").equals(CHECKED)) {
			if (data.get("txtPassword").equals(data.get("txtPasswordConfirm"))) {
				user = new User(new Role(Role.SUPER_ADMIN), new Login(data.get("txtEmail"), data.get("txtPassword")));
				user.getLogin().setUserNameByUserEmail();
				user.setGym(new Gym(data.get("txtNameGym")));
				if (LoginLogic.exists(user.getLogin())) {
					if (GymLogic.exists(user.getGym())) {
						result = "This user and gym already exists!";
					} else if (GymLogic.create(user.getGym())) {
						if (UserLogic.loadByEmail(user)) {
							if (UserLogic.relationalWithGym(user)) {
								return ok(SUCCESS_REQUEST);
							} else {
								result = "Error trying relational User with Gym!";
							}
						} else {
							result = "Error trying Load User!";
						}
					} else {
						result = "Error trying Create the Gym!";
					}
				} else if (LoginLogic.create(user.getLogin())) {
					if (!GymLogic.exists(user.getGym())) {
						if (GymLogic.create(user.getGym())) {
							if (UserLogic.create(user)) {
								if (UserLogic.relationalWithGym(user)) {
									return ok(SUCCESS_REQUEST);
								} else {
									result = "Error trying relational User with Gym!";
								}
							} else {
								result = "Error trying create Login!";
							}
						} else {
							result = "Error trying Create the Gym!";
						}
					} else {
						result = "This gym already exists!";
					}
				} else {
					result = "Error trying create Login!";
				}
			} else {
				result = "The password and its confirm are not the same!";
			}
		} else {
			result = "Please choose Terms and Conditions!";
		}
		return ok(result);
	}

	public static Result signIn() {		
		final Map<String, String> data = Utils.deserializeJson(dec(request().body().asText()));
		User user = new User(new Login(data.get("email"), data.get("password")), new Gym(data.get("gymName")));
		if (LoginLogic.signIn(user.getLogin(), user.getGym())) {
			if (UserLogic.loadByLogin(user)) {				
				session_start(user);				
				return ok(user.getDefaultAction().getUrl());
			} else {				
				return badRequest("Error tryning load user!");
			}
		} else {
			return ok(UNSUCCESSFULLY_REQUEST);
		}		
	}
}

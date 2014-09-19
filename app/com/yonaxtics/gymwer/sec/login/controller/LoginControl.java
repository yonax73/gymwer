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
import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.set.user.logic.UserLogic;

/**
 * Class : LoginControl.java<br/>
 * Copyright : (c) 2014<br/>
 * Company : yonaxtics<br/>
 * date : Sep 4, 2014<br/>
 * User : yonatan<br/>
 * 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class LoginControl extends securedController {

	public static Result login() {
		if (isAuthenticated()) {
			User user = getUserLoggedIn();
			if (user != null) {
				return redirect(user.getDefaultAction().getUrl());
			}
		}
		return ok(login.render());
	}

	public static Result signUp() {
		return ok(signup.render());
	}

	public static Result signOut() {
		sessionClear();
		return redirect("/login");
	}

	/**
	 * Solo se permite crear un super admin por gimnasio, en caso de que exista
	 * el gimansio y el usuario, o solo el gimnasio no se podra crear la cuenta.
	 * 
	 * @return
	 */
	public static Result createAccount() {
		String result = null;
		User user = null;
		final Map<String, String[]> data = request().body().asFormUrlEncoded();
		if (dec(data.get("cbxTerms")[0]).equals(CHECKED)) {
			if (data.get("txtPassword")[0].equals(data.get("txtPasswordConfirm")[0])) {
				user = new User(new Role(Role.SUPER_ADMIN), new Login(dec(data.get("txtEmail")[0]), data.get("txtPassword")[0]));
				user.setGym(new Gym(dec(data.get("txtNameGym")[0])));
				if (LoginLogic.exists(user.getLogin())) {
					if (GymLogic.exists(user.getGym())) {
						result = "This user and gym already exists!";
					} else if (GymLogic.create(user.getGym())) {
						if (UserLogic.loadByEmail(user)) {
							if (UserLogic.relationalWithGym(user)) {
								return ok(REQUEST_SUCCESS);
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
									return ok(REQUEST_SUCCESS);
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
		final Map<String, String[]> data = request().body().asFormUrlEncoded();
		User user = new User(new Login(dec(data.get("txtEmail")[0]), data.get("txtPassword")[0]), new Gym(dec(data.get("txtSiteName")[0])));		
		if (LoginLogic.signIn(user.getLogin(),user.getGym().getName())) {
			//Load object User
			createSession();
			user.getLogin().init();
			setCurrentLogin(user.getLogin());
			setUserLoggedIn(user);			
			return ok(user.getDefaultAction().getUrl());
		} else {
			return ok(REQUEST_BAD);
		}
	}
}

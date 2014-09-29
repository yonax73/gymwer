package com.yonaxtics.gymwer.set.user.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;

import java.util.Map;

import play.libs.Json;
import play.mvc.Result;
import views.html.dpa.user.user;
import views.html.dpa.user.users;

import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.sec.Persitence;
import com.yonaxtics.gymwer.sec.SecuredController;
import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.set.location.logic.LocationLogic;
import com.yonaxtics.gymwer.set.master.entity.Address;
import com.yonaxtics.gymwer.set.master.entity.Phone;
import com.yonaxtics.gymwer.set.master.logic.MasterLogic;
import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.set.user.logic.UserLogic;
import com.yonaxtics.gymwer.util.Utils;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class UserControl extends SecuredController {

	public static Result users() {
		return authenticated(users.render());
	}

	public static Result user() {
		return authenticated(user.render());
	}

	public static Result loadUser() {
		User user = user_loggedIn();
		if (user != null) {
			Login login = current_login();
			if (login != null) {
				user.setLogin(login);
				return authenticated(enc(Json.toJson(user).toString()));
			} else {
				return badRequest("The Login is Null");
			}
		}
		return badRequest("The user is Null");
	}

	public static Result saveUser() {
		String result = null;
		Role role = current_role();
		if (role != null) {
			if (role.isAuthorizedToUpdateUser()) {
				User user = user_loggedIn();
				if (user != null) {
					Login login = current_login();
					if (login != null) {
						user.setLogin(login);
						user.setRole(role);
						final Map<String, String> data = Utils.deserializeJson(dec(request().body().asText()));
						Phone phone = null;
						Address address = null;
						Location location = user.getLocation();
						if (location == null) {
							phone = new Phone(data.get("txtPhone"));
							address = new Address(data.get("txtAddress"));
							location = new Location(0);
						} else {
							phone = location.getPhone();
							address = location.getAddress();
							phone.setPhone(data.get("txtPhone"));
							address.setAddress(data.get("txtAddress"));
						}
						if (MasterLogic.save(phone)) {
							if (MasterLogic.save(address)) {
								if ((!phone.isEmpty() && phone.exists()) || (!address.isEmpty() && address.exists())) {
									location.setPhone(phone);
									location.setAddress(address);
									if (!LocationLogic.save(location)) {
										result = "Error trying save the address!";
									}
									user.setDocument(data.get("txtDocument"));
									user.setName(data.get("txtFirstName"));
									user.setLastName(data.get("txtLastName"));
									user.setLocation(location);
									user.getLogin().setName(data.get("txtNameUser"));
									user.getDefaultAction().setId(Integer.parseInt(data.get("txtDefaultActionId")));
								}
								if (UserLogic.update(user)) {
									Persitence.setObject(user.getSerial(), user);									
									Persitence.setObject(login.getSerial(), login);
									result = SUCCESS_REQUEST;
								} else {
									result = "Error trying save the user!";
								}
							} else {
								result = "Error trying save the address!";
							}
						} else {
							result = "Error trying save the phone!";
						}
					} else {
						return badRequest("The Login is Null");
					}

				} else {
					return badRequest("The user is Null");
				}
			} else {
				result = "You do not have permission to edit User information!";
			}
		} else {
			return badRequest("The role is Null");
		}
		return authenticated(result);
	}

}

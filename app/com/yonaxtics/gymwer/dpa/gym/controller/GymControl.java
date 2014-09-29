package com.yonaxtics.gymwer.dpa.gym.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;

import java.util.Map;

import play.libs.Json;
import play.mvc.Result;
import views.html.dpa.gym.gym;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.dpa.gym.logic.GymLogic;
import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.sec.SecuredController;
import com.yonaxtics.gymwer.set.location.logic.LocationLogic;
import com.yonaxtics.gymwer.set.master.logic.MasterLogic;
import com.yonaxtics.gymwer.util.Utils;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (9/8/2014)
 *
 */
public class GymControl extends SecuredController {

	public static Result gym() {
		return authenticated(gym.render());
	}

	public static Result load() {
		Role role = current_role();
		if (role != null) {
			if (role.isAuthorizedToLoadGym()) {
				Gym gym = current_gym();
				if (gym != null) {
					if (GymLogic.load(gym)) {
						return ok(enc(Json.toJson(gym).toString()));
					} else {
						return ok("Error trying Load Gym!");
					}
				} else {
					return badRequest("The gym is Null");
				}
			} else {
				return ok("You do not have permission to view gym information!");
			}
		} else {
			return badRequest("The role is Null");
		}
	}

	public static Result save() {
		String message = null;
		Role role = current_role();
		if (role != null) {
			if (role.isAuthorizedToUpdateGym()) {
				Gym gym = current_gym();
				if (gym != null) {
					final Map<String, String> data = Utils.deserializeJson(dec(request().body().asText()));
					gym.setName(data.get("txtName"));
					if (!gym.getName().isEmpty()) {
						gym.getLocation().getPhone().setPhone(data.get("txtPhone"));
						if (MasterLogic.save(gym.getLocation().getPhone())) {
							gym.getLocation().getAddress().setAddress(data.get("txtAddress"));
							if (MasterLogic.save(gym.getLocation().getAddress())) {
								if (LocationLogic.save(gym.getLocation())) {
									gym.setName(data.get("txtNit"));
									if (GymLogic.update(gym)) {
										message = SUCCESS_REQUEST;
									} else {
										message = "Error trying to save Gym!";
									}
								} else {
									message = "Error trying to save Location!";
								}
							} else {
								message = "Error trying to save Address!";
							}
						} else {
							message = "Error trying to save Phone!";
						}
					} else {
						message = "The user Name is required and can't empty";
					}
				} else {
					message = "The gym is Null";
				}
			} else {
				message = "You do not have permission to edit Gym information!";
			}
		} else {
			return badRequest("The role is Null");
		}
		return authenticated(message);
	}

}

	package com.yonaxtics.gymwer.dpa.gym.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;

import java.util.Map;

import play.libs.Json;
import play.mvc.Http.Context;
import play.mvc.Result;
import views.html.dpa.gym.gym;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.dpa.gym.logic.GymLogic;
import com.yonaxtics.gymwer.sec.SecuredController;
import com.yonaxtics.gymwer.set.location.logic.LocationLogic;
import com.yonaxtics.gymwer.set.master.logic.MasterLogic;
import com.yonaxtics.gymwer.set.user.entity.User;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (9/8/2014)
 *
 */
public class GymControl extends SecuredController {
	
	public static Result gym(){			 
			return authenticated(gym.render());			
	}	
	
	public static Result load(){				
		User user = user_loggedIn();
		if (user != null) {
			Gym gym = user.getGym();
			if (GymLogic.load(gym)) {
				Context.current().session().put(Gym.KEY, enc(gym.getSerial()));
				return ok(enc(Json.toJson(gym).toString()));
			} else {
				return ok("Error trying Load Gym!");
			}
		} else {
			return sign_out();
		}	
	}
	
	public static Result save() {
		String message = null;
		Gym gym = current_gym();
		if (gym != null) {
			final Map<String, String[]> data = request().body().asFormUrlEncoded();
			gym.setName(dec(data.get("txtName")[0]));
			if (!gym.getName().isEmpty()) {
				gym.getLocation().getPhone().setPhone((dec(data.get("txtPhone")[0])));
				if (MasterLogic.save(gym.getLocation().getPhone())) {
					gym.getLocation().getAddress().setAddress(dec(data.get("txtAddress")[0]));
					if (MasterLogic.save(gym.getLocation().getAddress())) {
						if (LocationLogic.save(gym.getLocation())) {
							gym.setName(dec(data.get("txtNit")[0]));
							if (GymLogic.update(gym)) {
								return ok(SUCCESS_REQUEST);
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
			return ok(message);

		} else {
			return sign_out();
		}
	}
		
	
	
	
}

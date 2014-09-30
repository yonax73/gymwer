package com.yonaxtics.gymwer.set.user.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;

import java.util.Map;

import play.libs.Json;
import play.mvc.Result;
import views.html.dpa.user.user;
import views.html.dpa.user.users;

import com.yonaxtics.gymwer.sec.SecuredController;
import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.set.location.logic.LocationLogic;
import com.yonaxtics.gymwer.set.master.entity.Address;
import com.yonaxtics.gymwer.set.master.entity.Phone;
import com.yonaxtics.gymwer.set.master.logic.MasterLogic;
import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.util.Utils;



/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class UserControl extends SecuredController {		
	
	public static Result users(){		
		return authenticated(users.render());		
	}
	
	public static Result user(){
		return authenticated(user.render());
	}
	
	public static Result loadUser(){
		User user = user_loggedIn();
		if(user!=null){
			Login login = current_login();
			if(login!=null){
				user.setLogin(login);
				return authenticated(enc(Json.toJson(user).toString()));
			}else{
			    return sign_out();    	
			}			
		}
		return sign_out();
	}
	
	
	public static Result saveUser() {
		String result = null;
		User user = user_loggedIn();
		if (user != null) {
			boolean hasPermission = user.getRole().isSuperAdmin();
			if (!hasPermission) {
				hasPermission = user.getRole().isAuthorizedToUpdateUser();
			}
			if (hasPermission) {
				Login login = current_login();
				if (login != null) {
					user.setLogin(login);
					final Map<String, String> data = Utils.deserializeJson(dec(request().body().asText()));
					Phone phone = null;
					Address address = null;
					Location location  = null;
					phone = new Phone(Integer.parseInt(data.get("txtPhoneId")),data.get("txtPhone"));
					if (MasterLogic.save(phone)) {
						address = new Address(Integer.parseInt(data.get("txtAddressId")),data.get("txtAddress"));
						if(MasterLogic.save(address)){
							location = new Location(Integer.parseInt(data.get("txtLocationId")));
							if((!phone.isEmpty() && phone.exists()) ||( !address.isEmpty() && address.exists())){
								location.setPhone(phone);
								location.setAddress(address);
								if(LocationLogic.save(location)){
									return ok();
								}	else {}
							}
						}else{
							return ok("Erro trying save the address!");
						}
					} else {						
						return ok("Error trying save the phone!");
					}
				} else {
					return sign_out();
				}
			} else {
				return ok("You do not have permission to edit User information!");
			}

		} else {
			return sign_out();
		}
		return null;
		
	}

}

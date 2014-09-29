package com.yonaxtics.gymwer.sec.permission.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import play.libs.Json;
import play.mvc.Result;

import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.sec.SecuredController;
import com.yonaxtics.gymwer.sec.permission.logic.PermissionLogic;

/**
 * Class : PermissionControl.java.java<br/>
 * Copyright : (c) 2014<br/>
 * Company : yonaxtics<br/>
 * date : Aug 15, 2014<br/>
 * User : YQ<br/>
 * 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */
public class PermissionControl extends SecuredController {

	public static Result loadNav() {
		Role role = current_role();
		if (role != null) {
			if (PermissionLogic.load(role)) {
				return authenticated(enc(Json.toJson(role.getPermissionsLoad()).toString()));
			} else {
				return ok("Error trying load the navegation!");
			}
		} else {
			return badRequest("The Role is Null");
		}
	}
}

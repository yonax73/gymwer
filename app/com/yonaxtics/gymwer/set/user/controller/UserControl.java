package com.yonaxtics.gymwer.set.user.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;

import java.util.Map;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.dpa.user.user;
import views.html.dpa.user.users;

import com.yonaxtics.gymwer.util.Utils;



/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class UserControl extends Controller {		
	
	public static Result users(){		
		return ok(users.render());		
	}
	
	public static Result user(){
		return ok(user.render());
	}
	
	public static Result loadUser(){
		return ok("ok");
	}
	
	
	public static Result saveUser(){		 
		final Map<String, String> data = Utils.deserializeJson(dec(request().body().asText()));		
		return ok(data.get("name"));
	}

}

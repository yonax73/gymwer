package com.yonaxtics.gymwer.util.list.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.yonaxtics.gymwer.sec.securedController;
import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.util.base.entity.Entity;
import com.yonaxtics.gymwer.util.list.entity.ListItem;
import com.yonaxtics.gymwer.util.list.entity.item.Item;
import com.yonaxtics.gymwer.util.list.logic.ListLogic;

/** 
 * Clase     : ListControl.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 29, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class ListControl extends Controller {

	
	public static Result personUrls(){		
//		ListItem urls = new ListItem(null,null);
//		if(ListLogic.loadPersonUrls(urls)){			
//			return ok(enc(Json.toJson(urls.getItems()).toString()));
//		}		
		return ok("Internal Error 6001");
	}	
	
	public static Result entityStates(){
		ListItem states = new ListItem();		
		states.add(new Item(Entity.ACTIVE, "ACTIVE"));
		states.add(new Item(Entity.INACTIVE, "INACTIVE"));
		return ok(enc(Json.toJson(states.getItems()).toString()));
	}
}

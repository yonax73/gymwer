package com.yonaxtics.gymwer.util.list.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import static com.yonaxtics.gymwer.util.Constant.SESSION_OK;

import com.yonaxtics.gymwer.set.person.entity.Person;
import com.yonaxtics.gymwer.util.list.entity.ListItem;
import com.yonaxtics.gymwer.util.list.logic.ListLogic;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

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
		ListItem urls = new ListItem(new Person(Integer.parseInt(dec(session(SESSION_OK)))));
		if(ListLogic.loadPersonUrls(urls)){			
			return ok(enc(Json.toJson(urls.getItems()).toString()));
		}		
		return ok("Internal Error 6001");
	}
}

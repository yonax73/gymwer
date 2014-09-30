package com.yonaxtics.gymwer.util.list.controller;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import play.libs.Json;
import play.mvc.Http.Context;
import play.mvc.Result;

import com.yonaxtics.gymwer.sec.SecuredController;
import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.set.master.entity.ActionType;
import com.yonaxtics.gymwer.set.user.entity.User;
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

public class ListControl extends SecuredController {

	
	
	public static Result actionsLoad(){	
		User user = user_loggedIn();
		if(user!=null){
			ListItem actions = new ListItem(user);
			if(ListLogic.loadActionsByUser(actions,ActionType.LOAD)){	
				Context.current().session().put(Action.ACTIONS_LOAD_LIST_KEY, enc(actions.getSerial()));
				return ok(enc(Json.toJson(actions.getItems()).toString()));
			}		
			return ok("Error trying load list actions load");			
		}else{
			return sign_out();
		}
	}	
	
	/**
	 * This list is persistent in the client web whit localStore
	 * @return
	 */
	public static Result entityStates(){
		ListItem states = new ListItem();		
		states.add(new Item(Entity.ACTIVE, "ACTIVE"));
		states.add(new Item(Entity.INACTIVE, "INACTIVE"));
		return ok(enc(Json.toJson(states.getItems()).toString()));
	}
}

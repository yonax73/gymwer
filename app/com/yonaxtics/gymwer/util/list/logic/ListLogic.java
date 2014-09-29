package com.yonaxtics.gymwer.util.list.logic;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import play.mvc.Http.Context;

import com.yonaxtics.gymwer.sec.Persitence;
import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.util.list.dao.ListDao;
import com.yonaxtics.gymwer.util.list.entity.ListItem;

/** 
 * Clase     : ListLogic.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 29, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class ListLogic {

	/**
	 * @param actions
	 * @return
	 */
	public static boolean loadActionsByUser(ListItem actions,int actionType) {
		boolean result = false;		
		if(actions != null &&   actions.getEntity() != null && actions.getEntity().exists()){
			result = Persitence.find(actions);
			if(!result){
				result = ListDao.loadActionsByUser(actions,actionType);
				if(result){					
					Persitence.setObject(actions.getSerial(), actions);
					Context.current().session().put(Action.ACTIONS_LOAD_LIST_KEY, enc(actions.getSerial()));					
				}
			}			
		}
		return result;
	}

}

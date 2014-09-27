package com.yonaxtics.gymwer.util.list.logic;

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
	 * @param urls
	 * @return
	 */
	public static boolean loadActionsByUser(ListItem urls,int actionType) {
		boolean result = false;		
		if(urls != null &&   urls.getEntity() != null && urls.getEntity().exists()){
			result = ListDao.loadActionsByUser(urls,actionType);
		}
		return result;
	}

}

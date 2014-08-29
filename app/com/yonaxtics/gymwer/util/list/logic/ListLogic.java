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
	public static boolean loadPersonUrls(ListItem urls) {
		boolean result = false;		
		if(urls != null && urls.getEntity().getId() > 0){
			result = ListDao.loadPersonUrls(urls);
		}
		return result;
	}

}

package com.yonaxtics.gymwer.sec.session;


import play.mvc.Http;
import play.Logger;

import com.yonaxtics.gymwer.util.base.entity.Entity;

/** 
 * Clase     : Session.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 11, 2014<br/> 
 * User      : YQ<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class Session {
       
	public static void setAttribute(String key,Entity entity){
		
		if(!(key.isEmpty() && entity==null)){
			play.mvc.Http.Session session = Http.Context.current().session();
			 session.put(key, entity.toString());			 
		}else{
            Logger.error("Value for " + key + " is null");
		}
	}
	
   public static Entity setAttribute(String key){
		return null;
	}
   
   public static void clear(){}
}

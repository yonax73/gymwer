package com.yonaxtics.gymwer.sec;



import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;
import play.cache.Cache;
import play.mvc.Http.Context;

import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.util.Utils;

/** 
 * Class     : CacheLogic.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 17, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class Persitence extends Cache {
      
	public static final int TIME_EXPIRED = 72 * 60 * 1000 * 60;          //(hours - minutes - seconds - milliseconds)

	
	public static Object getObject(String key){
		if(key!=null && !key.isEmpty()){
			return Utils.deserialize((byte[]) get(key));	
		}
		return null;
	}
	
	public static void setObject(String key,Object object){
		if(key!=null && !key.isEmpty() && object!=null){
			set(key, Utils.serialize(object),TIME_EXPIRED);			
		}		 
	}
	
	public static boolean find(User user) {
		boolean result = false;
		String serial = user.getSerial();
		User auxUser = (User) getObject(serial);
		if (auxUser != null && auxUser.exists()) {
			user.copy(auxUser);						
			Context.current().session().put(User.KEY, enc(serial));			
			result  = user.exists();
		}
		return result;
	}
	
}

package com.yonaxtics.gymwer.sec;


import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;

import play.Logger;
import play.Play;
import play.cache.Cache;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Http.Session;
import play.mvc.Security;

import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.util.Utils;
import com.yonaxtics.gymwer.util.base.entity.Entity;

/** 
 * Clase     : Secured.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 13, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class Secured extends Security.Authenticator {

	public static final String UNAUTHENTICATED = "UNAUTHENTICATED";
	private static final String LOGIN = "11508891YSA";
	private static final String TIME = "11508892YSA";
	
	protected static Login getLogin(){
		Login  login = null;
		if(!sessionTimeout()){
			String value =  Context.current().session().get(LOGIN);
			if(value != null){
				login = (Login) Utils.deserialize((byte[])Cache.get(dec(value)));
			}
		}		
		return login;
	}
	
	public static void setLogin(Login login){
		if(!(sessionTimeout() && login==null)){
		   String cacheKey = getCacheKey(LOGIN);	
		   Context.current().session().put(LOGIN, enc(cacheKey));
		   Cache.set(cacheKey, Utils.serialize(login));
		}
	}


	private static boolean sessionTimeout(){
		boolean result = false;
		Session currentSession = Context.current().session();
		if(currentSession.get(LOGIN)!=null){
			String previousTick = currentSession.get(TIME);
			if(previousTick!=null && !previousTick.isEmpty()){
				long previousT = Long.valueOf(previousTick);
				long currentT  = new Date().getTime();
				long timeout = (Long.valueOf(Play.application().configuration().getString("sessionTimeout"))*1000*60);
				if((currentT-previousT) > timeout){
					currentSession.clear();
					result = true;
				}
			}
		}
		if(!result){
			String tickString = Long.toString(new Date().getTime());
			currentSession.put(TIME, tickString);
		}		
		return result;
	}
	
	private static String getCacheKey(String key){
		return new String(key.concat(String.valueOf(System.currentTimeMillis())));
	}
}

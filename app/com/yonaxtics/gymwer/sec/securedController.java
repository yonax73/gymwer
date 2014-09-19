package com.yonaxtics.gymwer.sec;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;

import java.util.Date;

import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Http.Session;

import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.util.Utils;

/**
 * Clase : Session.java<br/>
 * Copyright : (c) 2014<br/>
 * Company : yonaxtics<br/>
 * date : Sep 11, 2014<br/>
 * User : YQ<br/>
 * 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class securedController extends Controller{
	
	/**
	 * AUTHENTICATION
	 */
	protected static final byte SESSION_EXPIRED = 3;
	protected static final byte SESSION_TIMEOUT = 2;
	protected static final byte UNAUTHENTICATED = 1;
	protected static final byte AUTHENTICATED = 0;
	protected static final String  NOTIFICATION[] = {"Authenticated","Unauthenticated","Session Timeout","Session Expired"};
	/**
	 * CLIENT WEB
	 */	
	protected final static String CHECKED = "on";		
	/**
	 * STATES REQUEST
	 */
	protected final static String REQUEST_SUCCESS = "19880511YSC+";
	protected final static String REQUEST_BAD = "19880512YSC+";
	/*
	 * KEY SESSION
	 */	
	private static final String KEY_LOGIN = "11508891YSC-";
	private static final String KEY_TIMEOUT = "11508892YSC-";
	private static final String KEY_SESSION_EXPIRED = "11508893YSC-";
	private static final String KEY_USER = "11508894YSC-";
	/**
	 * TIMES IN MILLISECONDS
	 */
	private static final int TIMEOUT = 15  * 60 * 1000;                  //(minutes - seconds - milliseconds)
	private static final int TIME_EXPIRED = 8 * 60 * 1000 * 60;          //(hours - minutes - seconds - milliseconds)
	
	protected static Login getCurrentLogin(){
		Login  login = null;
		if(!sessionTimeout()){
			String value =  Context.current().session().get(KEY_LOGIN);
			if(value != null){
				login = (Login) Utils.deserialize((byte[])Cache.get(dec(value)));
			}
		}		
		return login;
	}
	
	protected static void setCurrentLogin(Login login){
		if(!(sessionTimeout() && login==null)){
		   String serial = login.getSerial();	
		   Context.current().session().put(KEY_LOGIN, enc(serial));		   
		   Cache.set(serial, Utils.serialize(login));
		}
	}
	
	protected static User getUserLoggedIn(){		
		return  (User) Utils.deserialize((byte[])Cache.get(dec(Context.current().session().get(KEY_USER))));
	}
	
	protected static void setUserLoggedIn(User user){
         
	}
	
	protected static void createSession(){
		Session currentSession = Context.current().session();
		currentSession.put(KEY_SESSION_EXPIRED,enc(String.valueOf(TIME_EXPIRED)));
		currentSession.put(KEY_TIMEOUT,enc(String.valueOf(TIMEOUT)));
	}
	
	protected static byte authenticated(){		   
	    if(sessionExpired())return SESSION_EXPIRED;
		if(sessionTimeout())return SESSION_TIMEOUT;
		String value =  Http.Context.current().session().get(KEY_LOGIN);
		if(value==null) return UNAUTHENTICATED;
		if(Cache.get(dec(value))==null) return UNAUTHENTICATED;
		return AUTHENTICATED;
	}
	
	protected static boolean isAuthenticated(){
		return authenticated() == AUTHENTICATED;
	}

	protected static boolean sessionTimeout(){
		boolean result = false;
		Session currentSession = Context.current().session();
		if(currentSession.get(KEY_TIMEOUT)!=null){
			String previousTick = dec(currentSession.get(KEY_TIMEOUT));
			if(previousTick!=null && !previousTick.isEmpty()){
				long previousT = Long.valueOf(previousTick);
				long currentT  = new Date().getTime();			
				if((currentT-previousT) > TIMEOUT){					
					result = true;
					sessionClear();
				}
			}
		}	
		return result;
	}
	protected static boolean sessionExpired(){
		boolean result = false;
		Session currentSession = Context.current().session();
		if(currentSession.get(KEY_SESSION_EXPIRED)!=null){
			String previousTick = dec(currentSession.get(KEY_SESSION_EXPIRED));
			if(previousTick!=null && !previousTick.isEmpty()){
				long previousT = Long.valueOf(previousTick);
				long currentT  = new Date().getTime();			
				if((currentT-previousT) > TIME_EXPIRED){					
					result = true;
					sessionClear();
				}
			}
		}
		return result;
	}
	
	
	protected static void sessionClear(){	
		  Session currentSession = Http.Context.current().session();
		  String value =  currentSession.get(KEY_LOGIN);
		  Login login = value !=null ? (Login) Utils.deserialize((byte[])Cache.get(dec(value))) : null;		  
		  if(login!=null)login.destroy();
		  currentSession.entrySet().stream().parallel().forEach(key->{Cache.remove(dec(key.getValue()));});
		  currentSession.clear();
	}

	
}

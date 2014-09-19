package com.yonaxtics.gymwer.sec;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;

import java.util.Date;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Http.Session;
import play.mvc.Result;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.set.user.entity.User;

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
	protected final static String SUCCESS_REQUEST = "success_Request";
	protected final static String BAD_REQUEST = "bad_Request";
	/*
	 * KEY SESSION
	 */	
	private static final String KEY_TIMEOUT = "KEY_TIMEOUT";
	private static final String KEY_SESSION_EXPIRED = "KEY_SESSION_EXPIRED";
	
	/**
	 * TIMES IN MILLISECONDS
	 */
	private static final long TIMEOUT = 15  * 60 * 1000;                  //(minutes - seconds - milliseconds)
	private static final long TIME_EXPIRED = 8 * 60 * 60 * 1000;          //(hours - minutes - seconds - milliseconds)
	
	
	protected static Login getCurrentLogin(){		
		return (Login) Persitence.getObject(dec(Context.current().session().get(Login.KEY)));
	}
	
	protected static void setCurrentLogin(Login login){		
		   String serial = login.getSerial();	
			Session session = Context.current().session();
			if (!session.containsKey(Login.KEY)) {
				session.put(Login.KEY, enc(serial));
			}   	
		   Persitence.setObject(serial, login);
	}
	
	protected static User getUserLoggedIn(){		
		return  (User) Persitence.getObject(dec(Context.current().session().get(User.KEY)));
	}
	
	public static void setUserLoggedIn(User user){
        String serial = user.getSerial();         
		Session session = Context.current().session();
		if (!session.containsKey(User.KEY)) {
			session.put(User.KEY, enc(serial));
		}
		Persitence.setObject(serial, user);
	}	
 	
 	protected static Gym getCurrentGym(){
 		return (Gym) Persitence.getObject(dec(Context.current().session().get(Gym.KEY)));
 	}
	
	protected static void createSession(){
		Session currentSession = Context.current().session();
		currentSession.put(KEY_SESSION_EXPIRED,enc(String.valueOf(TIME_EXPIRED)));
		currentSession.put(KEY_TIMEOUT,enc(String.valueOf(TIMEOUT)));
	}
	
	protected static byte authenticated(){		   
	    if(sessionExpired())return SESSION_EXPIRED;
		if(sessionTimeout())return SESSION_TIMEOUT;
		String value =  Http.Context.current().session().get(Login.KEY);
		if(value==null) return UNAUTHENTICATED;
		if(Persitence.get(dec(value))==null) return UNAUTHENTICATED;
		return AUTHENTICATED;
	}
	
	protected static boolean isAuthenticated(){
		return authenticated() == AUTHENTICATED;
	}
	
	protected static Result onUnauthorized(byte result){
		sessionDestroy();		
		return unauthorized(views.html.sec.error.unauthorized.render(),NOTIFICATION[result]);
	}
	
	protected static Result closeSession(){
		sessionDestroy();
		return redirect("/login");
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
					sessionDestroy();
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
					sessionDestroy();
				}
			}
		}
		return result;
	}	
	
	protected static void sessionDestroy(){	
		  Session currentSession = Http.Context.current().session();
		  String value =  currentSession.get(Login.KEY);
		  Login login = value !=null ? (Login) Persitence.getObject(dec(value)) : null;		  
		  if(login!=null)login.destroy();
		  Persitence.remove(value);		  
		  currentSession.clear();
	}

	
}

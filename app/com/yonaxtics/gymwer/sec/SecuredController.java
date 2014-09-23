package com.yonaxtics.gymwer.sec;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;

import java.time.Duration;
import java.time.LocalDateTime;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Http.Session;
import play.mvc.Result;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.set.user.entity.User;

/**
 * Class : SecuredController.java<br/>
 * Copyright : (c) 2014<br/>
 * Company : yonaxtics<br/>
 * date : Sep 11, 2014<br/>
 * User : YQ<br/>
 * 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class SecuredController extends Controller{
	
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
	/**
	 * TIMES IN MINUTES
	 */
	private static final long TIMEOUT = 15;                  //(minutes)
	private static final long TIME_EXPIRED = 8 * 60;         //(hours - minutes)
	
	
	protected static Login current_login(){		
		return (Login) Persitence.getObject(dec(Context.current().session().get(Login.KEY)));
	}
	
	protected static User user_loggedIn(){		
		return  (User) Persitence.getObject(dec(Context.current().session().get(User.KEY)));
	}
 	
 	protected static Gym current_gym(){
 		return (Gym) Persitence.getObject(dec(Context.current().session().get(Gym.KEY)));
 	}
	
	protected static void session_start(User user){
		Session currentSession = Context.current().session();
		Login login  = user.getLogin();		
		String serialLogin = login.getSerial();
		login.init();		
		Persitence.setObject(serialLogin, login);
		currentSession.put(Login.KEY, enc(serialLogin));
		currentSession.put(User.KEY, enc(user.getSerial()));
	}
	
	protected static byte authenticated(){		   
	    if(session_expired())return SESSION_EXPIRED;
		if(session_timeout())return SESSION_TIMEOUT;
		String value =  Http.Context.current().session().get(Login.KEY);
		if(value==null) return UNAUTHENTICATED;
		if(Persitence.get(dec(value))==null) return UNAUTHENTICATED;
		return AUTHENTICATED;
	}
	
	protected static boolean is_authenticated(){
		return authenticated() == AUTHENTICATED;
	}
	
	protected static Result sign_out(){
		session_destroy();
		return redirect("/login");
	}

	protected static boolean session_timeout(){
		boolean result = false;					
		Login login = (Login) Persitence.getObject(dec(Context.current().session().get(Login.KEY)));			
		if(login!=null){	
			if((Duration.between(login.getCreated(), LocalDateTime.now()).toMinutes()) > TIMEOUT){					
				result = true;					
			}
		}
		return result;
	}
	
	protected static boolean session_expired(){
		boolean result = false;					
		Login login = (Login) Persitence.getObject(dec(Context.current().session().get(Login.KEY)));			
		if(login!=null){	
			if((Duration.between(login.getCreated(), LocalDateTime.now()).toMinutes()) > TIME_EXPIRED){					
				result = true;					
			}
		}
		return result;
	}	
	
	protected static void session_destroy(){	
		  Session currentSession = Http.Context.current().session();
		  String value =  currentSession.get(Login.KEY);
		  Login login = value !=null ? (Login) Persitence.getObject(dec(value)) : null;		  
		  if(login!=null)login.destroy();
		  Persitence.remove(value);		  
		  currentSession.clear();
	}
	
	protected static void session_destroy(byte result){	
		  Session currentSession = Http.Context.current().session();
		  String value =  currentSession.get(Login.KEY);
		  Login login = value !=null ? (Login) Persitence.getObject(dec(value)) : null;		  
		  if(login!=null)login.destroy(NOTIFICATION[result]);
		  Persitence.remove(value);		  
		  currentSession.clear();
	}

	
}

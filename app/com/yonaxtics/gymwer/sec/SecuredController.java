package com.yonaxtics.gymwer.sec;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;

import java.time.Duration;
import java.time.LocalDateTime;

import play.Logger;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Http.Session;
import play.mvc.Result;
import play.twirl.api.Html;

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
	private static final long TIMEOUT = 15;                   //(minutes)
	private static final long TIME_EXPIRED = 10;         //(hours - minutes)
	
	
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
	
	protected static Result authenticated(Html view) {
		try {
			if (session_expired()) {
				session_destroy("Session Expired");
				return expired();
			}
			if (session_timeout()) {
				session_destroy("Session Timeout");
				return timeout();
			}
			String value = Http.Context.current().session().get(Login.KEY);
			if (!(value != null && Persitence.get(dec(value)) != null)) {
				session_destroy("Unauthenticated");
				return unauthenticated();
			}
			return ok(view);
		} catch (Exception e) {
			session_destroy(e.getMessage());
			return unauthenticated();
		}
	}
	
	protected static Result authenticated(String arg0) {
		try {
			if (session_expired()) {
				session_destroy("Session Expired");
				return expired();
			}
			if (session_timeout()) {
				session_destroy("Session Timeout");
				return timeout();
			}
			String value = Http.Context.current().session().get(Login.KEY);
			if (!(value != null && Persitence.get(dec(value)) != null)) {
				session_destroy("Unauthenticated");
				return unauthenticated();
			}
			return ok(arg0);
		} catch (Exception e) {
			session_destroy(e.getMessage());
			return unauthenticated();
		}
	}
	
	protected static Result authenticatedLogin(Html view) {
		try {
			if (session_expired()) {
				session_destroy("Session Expired");
				return expired();
			}
			if (session_timeout()) {
				session_destroy("Session Timeout");
				return timeout();
			}
			User user = user_loggedIn();
			if (user != null) {
				return redirect(user.getDefaultAction().getUrl());
			}
			return ok(view);
		} catch (Exception e) {
			session_destroy(e.getMessage());
			return unauthenticated();
		}
	}

	
	protected static Result unauthenticated() {		
		return unauthorized(views.html.sec.error.unauthorized.render());
	}
	
	protected static Result expired() {		
		return unauthorized(views.html.sec.error.expired.render());
	}
	
	protected static Result timeout() {		
		return unauthorized(views.html.sec.error.timeout.render());
	}
	
	protected static Result sign_out(){
		session_destroy("Session closed by User");
		return redirect("/login");
	}

	protected static boolean session_timeout() {
		boolean result = false;
		try {
			Login login = (Login) Persitence.getObject(dec(Context.current().session().get(Login.KEY)));
			LocalDateTime now = LocalDateTime.now();
			if (login != null) {
				if ((Duration.between(login.getTimeout(), now).toMinutes()) > TIMEOUT) {
					result = true;
				}else{
					login.setTimeout(now);
					Persitence.setObject(login.getSerial(), login);
				}
			}
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
		return result;
	}
	
	protected static boolean session_expired(){
		boolean result = false;
		try {
			Login login = (Login) Persitence.getObject(dec(Context.current().session().get(Login.KEY)));
			if (login != null) {
				if ((Duration.between(login.getCreated(), LocalDateTime.now()).toMinutes()) > TIME_EXPIRED) {
					result = true;
				}
			}
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
		return result;
	}	
	

	
	protected static void session_destroy(String cause) {
		try {
			Session currentSession = Http.Context.current().session();
			String value = currentSession.get(Login.KEY);
			if (value != null) {
				Persitence.remove(value);
				Login login = (Login) Persitence.getObject(dec(value));
				if (login != null) {
					login.destroy(cause);
				}
			}
			currentSession.clear();
		} catch (Exception e) {
			Logger.error(e.getMessage());
		}
	}

	
}

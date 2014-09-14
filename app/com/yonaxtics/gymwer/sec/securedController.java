package com.yonaxtics.gymwer.sec;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import play.Logger;
import play.Play;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Http.Session;

import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.util.base.entity.Entity;

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
	
	private static final String LOGIN = "11508891YSA";
	private static final String TIME = "11508892YSA";
	
	protected static Login getLogin(){
		Login  login = null;
		if(!sessionTimeout()){
			String value =  Context.current().session().get(LOGIN);
			if(value != null){
				login = (Login) Entity.deserialize((byte[])Cache.get(dec(value)));
			}
		}		
		return login;
	}
	
	protected static void setLogin(Login login){
		if(!(sessionTimeout() && login==null)){
		   String cacheKey = getCacheKey(LOGIN);	
		   Context.current().session().put(LOGIN, enc(cacheKey));
		   Cache.set(cacheKey, login.serialize());
		}
	}
	
	protected static void clear(){	
		  Session currentSession = Http.Context.current().session();
		  String value =  currentSession.get(LOGIN);
		  Login login = value !=null ? (Login) Entity.deserialize((byte[])Cache.get(dec(value))) : null;		  
		  if(login!=null)login.destroy();
		  currentSession.entrySet().stream().parallel().forEach(key->{Cache.remove(dec(key.getValue()));});
		  currentSession.clear();
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
					clear();
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

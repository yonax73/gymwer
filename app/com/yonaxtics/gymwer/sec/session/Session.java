package com.yonaxtics.gymwer.sec.session;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import play.Logger;
import play.cache.Cache;
import play.mvc.Http;

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

public class Session {

	public final static String LOGIN = "11508891YS";
	private final static int TIME = 60 * 360;
	
	public static void setAttribute(String key, Entity entity) {
		try {
			if (!(key.isEmpty() && entity == null)) {							
		        ByteArrayOutputStream bos = new ByteArrayOutputStream();
		        ObjectOutputStream oos = new ObjectOutputStream(bos);
		        oos.writeObject(entity);
		        oos.flush();
		        oos.close();
		        bos.close();
		        byte[] data = bos.toByteArray();	
		        String cacheKey = key.concat(key.concat(String.valueOf(System.currentTimeMillis())));
		        Http.Context.current().session().put(key,enc(cacheKey));
		        Cache.set(cacheKey, data,TIME);		       
			} else {
				Logger.error("Value for " + key + " is null");
			}
		} catch (IOException ex) {
			Logger.error(ex.getMessage());
		}
	}
	
	public static Entity getAttribute(String key) {
		Entity entity = null;
		try {
			if (!key.isEmpty()) {		       
				byte [] data = (byte[]) Cache.get(dec(Http.Context.current().session().get(key)));
				if(data!=null){
					ByteArrayInputStream 	bais = new ByteArrayInputStream(data);
					ObjectInputStream ins = new ObjectInputStream(bais);
					entity = (Entity) ins.readObject();
					ins.close();
					bais.close();					
				}
			} else {
				Logger.error("Value for " + key + " is null");
			}
		} catch (IOException ex) {
			Logger.error(ex.getMessage());			
		} catch (ClassNotFoundException ex) {			
			Logger.error(ex.getMessage());
		}
		return entity;
	}
	
	public static boolean exists(String key){
		boolean result = false;
		if (!key.isEmpty())result = Http.Context.current().session().get(key) != null;		
		return result;
	}
	
	public static void clear(){	
		  play.mvc.Http.Session session = Http.Context.current().session();
		  Login login = (Login) getAttribute(LOGIN);
		  if(login!=null)login.destroy();
		  session.entrySet().stream().parallel().forEach(key->{Cache.remove(dec(key.getValue()));});
		  session.clear();
	}
	
}

package com.yonaxtics.gymwer.sec.session;

import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.dec;
import static com.yonaxtics.gymwer.sec.crypto.aes.Sec.enc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import play.Logger;
import play.cache.Cache;
import play.mvc.Http;


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

	public final static String OK = "11508891YSM";	
	public static int COUNTER;
	
	private final static String ID = "11508892YSM";	
	private static List<DataSession> dataSessionList = new ArrayList<DataSession>();	
	
	public static void start(){
         COUNTER++;
         DataSession dataSession = new DataSession(COUNTER);
         dataSessionList.add(dataSession);              
         Http.Context.current().session().put(ID,enc(String.valueOf(COUNTER)));
         StringBuffer strBf = new StringBuffer("New Session ( ");
         strBf.append(dataSession.getSerialId());
         strBf.append(" ) ");
         strBf.append(" has been initialized at the ");
         strBf.append(dataSession.getStartTime().toString());
         strBf.append(" since Client ");         
         strBf.append(dataSession.getHostAddress()); 		
 		 strBf.append("\nActive sessions ");
 		 strBf.append(String.valueOf(COUNTER));
 		 Logger.info(strBf.toString());
	}
	
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
		        DataSession dataSession = dataSessionList.get(Integer.parseInt(dec(Http.Context.current().session().get(ID)))-1);
		        dataSession.setKey(key);
		        Cache.set(dataSession.getKey(), data);		       
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
		        DataSession dataSession = dataSessionList.get(Integer.parseInt(dec(Http.Context.current().session().get(ID)))-1);
		        dataSession.setKey(key);
				byte [] data = (byte[]) Cache.get(dataSession.getKey());
				ByteArrayInputStream 	bais = new ByteArrayInputStream(data);
				ObjectInputStream ins = new ObjectInputStream(bais);
				entity = (Entity) ins.readObject();
				ins.close();
				bais.close();
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
		if (!key.isEmpty()) {
			if(Http.Context.current().session()!=null){
				if(Http.Context.current().session().get(ID)!=null){
					if(dataSessionList.size()>0){
						result = dataSessionList.get(Integer.parseInt(dec(Http.Context.current().session().get(ID)))-1) != null;	
					}					
				}				
			}									 
		}
		return result;
	}
	
	public static void end(){
		COUNTER--;		
		DataSession dataSession = dataSessionList.get(Integer.parseInt(dec(Http.Context.current().session().get(ID)))-1);			
		StringBuffer strBf = new StringBuffer("Session has been ended at the ");
		strBf.append(LocalDateTime.now().toString());	
		strBf.append("since Client ");
		strBf.append(dataSession.getHostAddress());
		strBf.append("and was logged  ");
		strBf.append(String.valueOf(dataSession.getTimeConnection()));
		strBf.append("minutes.");
		strBf.append("\nActive sessions");
		strBf.append(String.valueOf(COUNTER));
		dataSession.getKeyList().stream().parallel().forEach(key->{			
			Cache.remove(new String(key.concat(dataSession.getSerialId())));
		});
		dataSession.finalize();
		dataSessionList.remove(dataSession);
		Http.Context.current().session().clear();
		Logger.info(strBf.toString());
	}
	
	






}

package com.yonaxtics.gymwer.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import play.Logger;

/** 
 * Class     : Utils.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 17, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */
public class Utils {
	
	public static byte[] serialize(Object object){
		byte[] data  = null;
		try {										
		        ByteArrayOutputStream bos = new ByteArrayOutputStream();
		        ObjectOutputStream oos = new ObjectOutputStream(bos);
		        oos.writeObject(object);
		        oos.flush();
		        oos.close();
		        bos.close();
		        data = bos.toByteArray();			
		} catch (IOException ex) {
			Logger.error(ex.getMessage());
		}
		return data;		
	}
	
	public static Object deserialize(byte[]data){
		Object object = null;
		try {				
			if(data!=null){
				ByteArrayInputStream bais = new ByteArrayInputStream(data);
				ObjectInputStream ins = new ObjectInputStream(bais);
				object = (Object) ins.readObject();
				ins.close();
				bais.close();					
			}			
		} catch (IOException ex) {
			Logger.error(ex.getMessage());			
		} catch (ClassNotFoundException ex) {			
			Logger.error(ex.getMessage());
		}
		return object;
	}
}

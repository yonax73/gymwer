package com.yonaxtics.gymwer.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;

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
	public static String getNetworkInfoClient(){
		String hostAddress = null;
		Enumeration<NetworkInterface> net = null;			
		try {				
			net = NetworkInterface.getNetworkInterfaces();	
			while (net.hasMoreElements()) {	
				NetworkInterface element = net.nextElement();	
				Enumeration<InetAddress> addresses = element.getInetAddresses();	
				while (hostAddress== null && addresses.hasMoreElements()) {	
					InetAddress ip = addresses.nextElement();	
					if (ip instanceof Inet4Address || ip instanceof Inet6Address) {	
						if (ip.isSiteLocalAddress()) {	
							hostAddress = ip.getHostAddress();
						}	
					}	
				}	
			}				
		} catch (SocketException e) {				
			Logger.error(e.getMessage());
		}
		return hostAddress;
	}
	public static HashMap<String, String> deserializeJson(String json){		
		json =  StringUtils.replace(json, "{","");
		json =  StringUtils.replace(json, "}","");		
		HashMap<String, String> data = new HashMap<String, String>();		
		Arrays.asList(json.split("\",\"")).stream().parallel().forEach(value->{
			String node[] = StringUtils.replace(value, "\"","").split(":");				
			data.put(node[0], node[1]);
		});				
		return data;
	}
}

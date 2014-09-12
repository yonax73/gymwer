package com.yonaxtics.gymwer.sec.session;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import play.Logger;

/** 
 * Clase     : Session.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 11, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class DataSession {
        
	private String serialId; 
	private LocalDateTime startTime;	
	private  String hostAddress;	
	private int index;
	private List<String> keyList;


	private String key;
	
	public DataSession(int index){
		this.index = index;
		networkInfoClient();
		serialId = generateSerialId();
		startTime = LocalDateTime.now();
		keyList = new ArrayList<String>();
	}
	
	public DataSession(){		
		networkInfoClient();
		serialId = generateSerialId();
		startTime = LocalDateTime.now();
	}
	
	private  void networkInfoClient(){
		Enumeration<NetworkInterface> net = null;			
		try {				
			net = NetworkInterface.getNetworkInterfaces();	
			while (net.hasMoreElements()) {	
				NetworkInterface element = net.nextElement();	
				Enumeration<InetAddress> addresses = element.getInetAddresses();	
				while (getHostAddress() == null && addresses.hasMoreElements()) {	
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
	}
	
	public void finalize(){
		serialId = null;
		startTime = null;
		hostAddress = null;
		index = 0;
		key =null;
	}
	
	private   String generateSerialId(){		
		StringBuffer serialId = new StringBuffer(String.valueOf(System.currentTimeMillis()));
		serialId.append("_");
		serialId.append(String.valueOf(index));
		return  serialId.toString();	
	}

	public String getKey() {
		StringBuffer strBf = new StringBuffer(key);		
		strBf.append(serialId);
		return strBf.toString();
	}

	public void setKey(String key) {
		if(!keyList.contains(key)){
			keyList.add(key);
		}
		this.key = key;
	}

	public  long getTimeConnection() {
		return  Duration.between(startTime, LocalDateTime.now()).toMinutes();
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public String getHostAddress() {
		return hostAddress;
	}
	
	public String getSerialId(){
		return serialId;
	}
	
	public List<String> getKeyList() {
		return keyList;
	}
	
}

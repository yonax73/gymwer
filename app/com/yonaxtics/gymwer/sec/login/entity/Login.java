package com.yonaxtics.gymwer.sec.login.entity;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

import play.Logger;

import com.yonaxtics.gymwer.set.person.entity.Person;
import com.yonaxtics.gymwer.util.base.entity.Entity;

/** 
 * Clase     : Login.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 4, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class Login extends Entity{
	
	private static int counter;	
	private Person person;//quit
	private  String hostAddress;	
	
	public Login(Person person) {		
		super(0);
		this.person = person;
		
	}
	
	private  void networkInfoClient(){
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
	}
	
	public void start(){
		created = LocalDateTime.now();
		networkInfoClient();
        StringBuffer strBf = new StringBuffer("New Session [- ");
        strBf.append(String.valueOf(id));
        strBf.append(" -] ");
        strBf.append("has been initialized at the ");
        strBf.append(getFormatCreated());
        strBf.append(" for the User [- ");
        strBf.append(person.getUser().getEmail());
        strBf.append(" -] ");        
        strBf.append(" since Client ");         
        strBf.append(hostAddress); 		
	    strBf.append("\nActive sessions ");
	    strBf.append(String.valueOf(counter));
		Logger.info(strBf.toString());
	}
	

	
	public void destroy(){
		counter--;
		StringBuffer strBf = new StringBuffer("Session [-");		
        strBf.append(String.valueOf(id));
        strBf.append(" -] ");
        strBf.append("has been ended at the ");
		strBf.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        strBf.append(" for the User [- ");
        strBf.append(person.getUser().getEmail());
        strBf.append(" -] ");        
		strBf.append("since Client ");
		strBf.append(hostAddress);
		strBf.append(" and was logged ");
		strBf.append(String.valueOf(getTimeConnection()));
		strBf.append(" minutes aprox.");
		strBf.append("\nActive sessions");
		strBf.append(String.valueOf(counter));
		Logger.info(strBf.toString());
	}
	
	public  long getTimeConnection() {
		return  Duration.between(created, LocalDateTime.now()).toMinutes();
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public void finalize(){
         person = null;
         created = null;
         hostAddress = null;
         id= 0;
	}


}

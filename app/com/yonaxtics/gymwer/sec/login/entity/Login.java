package com.yonaxtics.gymwer.sec.login.entity;

import static com.yonaxtics.gymwer.util.Utils.getNetworkInfoClient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import play.Logger;

import com.yonaxtics.gymwer.util.base.entity.Entity;

/** 
 * Clase     : Login.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 4, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

@SuppressWarnings("serial")
public class Login extends Entity{
	
	private static int counter;	
	private String name;
	private String email;
	private transient String password;
	private  String hostAddress;	
	
	public Login(String email,String password) {		
		super(0);		
		this.email = email;
		this.password = password;
		this.name = email.split("@")[0];
		
	}

	@Override
	public boolean isEmpty() {		
		return email == null || email == "";
	}
	
	public void init(){
		counter++;
		created = LocalDateTime.now();
		hostAddress = getNetworkInfoClient();
        StringBuffer strBf = new StringBuffer("New Session [- ");
        strBf.append(String.valueOf(id));
        strBf.append(" -] ");
        strBf.append("has been initialized at the ");
        strBf.append(getFormatCreated());
        strBf.append(" for the User [- ");
        strBf.append(email);
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
        strBf.append(email);
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHostAddress() {
		return hostAddress;
	}

}

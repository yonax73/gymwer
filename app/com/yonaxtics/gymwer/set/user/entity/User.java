package com.yonaxtics.gymwer.set.user.entity;

import com.yonaxtics.gymwer.set.master.entity.*;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class User extends MasterValue {

	public User(int id) {
		
		super(id);
		
	}
	
	public User(int id, String user){
		
		super(id);
		setValue1(user);
		
		
	}
	
	public User(String user, String password){
		
		super(0);
		setValue1(user);
		setValue2(password);
		
	}
	
	public User(int id, String user, String password){
		
		super(id);
		setValue1(user);
		setValue2(password);
		
	}
	
	public User(String password){
		
		super(0);
		setValue2(password);
	}
	
	
	
	public String getName(){
		
		return getValue1();
	}
	
	public void setName(String name){
		
		setValue1(name);
	}
	
	public String getPassword(){
		
		return getValue2();
	}
	
	public void setPassword(String password){
		
		setValue2(password);
	}

}

package com.yonaxtics.gymwer.set.user.entity;

import com.yonaxtics.gymwer.set.master.entity.MasterValue;
import com.yonaxtics.gymwer.dpa.role.entity.Role;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class User extends MasterValue {

	
	private Role role;
	
	
	public User(int id) {
		
		super(id);		
	}
	
	public User(String email,String password,Role role){
		
		super(0);		
		setValue2(password);
		setValue3(email);
		this.role = role;
		
		extractNameFromEmail();
		
	}
	
	
	public User(String email,String password){
		
		super(0);	
		setValue2(password);
		setValue3(email);
	
		
	}


	/**
	 * @param session
	 */
	public User(String name) {
          
		super(0);
		setName(name);
	}

	private void extractNameFromEmail(){		
		
	    setName(getEmail().split("@")[0]);
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
	
	
	public String getEmail(){
		
		return getValue3();
	}
	
	
	public void setEmail(String email){
	    
		setValue3(email);
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}
	
	


}

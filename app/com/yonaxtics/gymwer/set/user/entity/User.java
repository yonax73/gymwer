package com.yonaxtics.gymwer.set.user.entity;

import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.util.base.entity.Entity;
import com.yonaxtics.gymwer.dpa.role.entity.Role;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class User extends Entity {

	
	private Role role;
	private String name;
	private String password;
	private String email;
	private Action defaultAction;
	
	
	public User(int id) {
		
		super(id);		
	}
	
	public User(String email,String password,Role role){
		
		super(0);				
		this.role = role;
		this.email = email;
		this.password = password;			
		extractNameFromEmail();
		
	}
	
	
	public User(String email,String password){
		
		super(0);	
		this.email =email;
		this.password = password;
		
	}

	public User(int id,String name) {
          
		super(id);
		this.name = name;
	}
	


	private void extractNameFromEmail(){		
		
	    setName(getEmail().split("@")[0]);
	}
	
	
	public boolean isEmpty() {
		
		return name == null
		   || name == ""	
		   || email == null 
           || email == "" 
		   || password == null
		   || password == "";
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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public Action getDefaultAction() {
		return defaultAction;
	}

	public void setDefaultAction(Action defaultAction) {
		this.defaultAction = defaultAction;
	}



	
	
	
	


}

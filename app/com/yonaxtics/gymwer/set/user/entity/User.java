package com.yonaxtics.gymwer.set.user.entity;

import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.set.person.entity.Person;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
@SuppressWarnings("serial")
public class User extends Person {	

	private Role role;
	private Action defaultAction;	
	
	public User(int id) {		
		super(id);		
	}

	public User(int id,String name) {          
		super(id);
		this.name = name;
	}
	
	public User(String name) {        
		super(0);
		this.name = name;
	}
	
	/**
	 * @param role
	 * @param login
	 */
	public User(Role role, Login login) {
        this.role = role;
        this.login = login;
	}

	public boolean isEmpty() {		
		return name == null
		   || name == "";
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

	public Action getDefaultAction() {
		return defaultAction;
	}

	public void setDefaultAction(Action defaultAction) {
		this.defaultAction = defaultAction;
	}



	
	
	
	


}

package com.yonaxtics.gymwer.set.user.entity;

import play.Logger;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.set.person.entity.Person;
import com.yonaxtics.gymwer.util.base.entity.Entity;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
@SuppressWarnings("serial")
public class User extends Person {	

	
	public static final String KEY = "USER_KEY";
	
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

	public User(Login login, Gym gym) {
		this.login = login;
		this.gym = gym;
	}
	
	@Override
	public String getSerial(){
		return new String(this.getClass().getName().concat(":").concat(login.getEmail()));
	}
	
	@Override
	public User clone(){
		User user = null;
		try {
			user =  (User) super.clone();
		} catch (CloneNotSupportedException e) {			
			Logger.info(e.getMessage());
		}
		return user;
	}	
	
    @Override
	public boolean isEmpty() {		
		return name == null
		   || name == "";
	}
    
    /**
     * Create object copy, with an exception in the object Login.
     */
	@Override
	public void copy(Entity entity) {
		User user = (User) entity;
		this.active = user.active;
		this.created = user.created;
		this.defaultAction = user.defaultAction;
		this.document = user.document;
		this.gym = user.gym;
		this.id = user.id;
		this.lastName = user.lastName;
		this.location = user.location;
		this.name = user.name;
		this.picture = user.picture;
		this.role = user.role;		
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

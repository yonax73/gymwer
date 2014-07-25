package com.gymwer.set.logic.user;

import com.gymwer.set.dao.user.UserDao;
import com.gymwer.set.entity.User;

public  class   UserLogic{

	
	  
	
	
	
	public static  boolean createAccount(User user){
		
		boolean result = false;
		
	      if(!user.isNull() && !user.exists()){
	    	  
	    	  result = UserDao.createUser(user);
	      }
	      
	    return result;
	}
	
	
}

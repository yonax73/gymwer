package com.yonaxtics.gymwer.set.user.logic;



import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.set.user.dao.UserDao;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public  class   UserLogic{

	
	  
	
	
	
	public static  boolean createAccount(User user){
		
		boolean result = false;
		
	      if(!user.isNull() && !user.exists()){
	    	  
	    	  result = UserDao.createUser(user);
	      }
	      
	    return result;
	}
	
	
}

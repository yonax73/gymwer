package com.yonaxtics.gymwer.set.user.logic;



import com.yonaxtics.gymwer.set.user.dao.UserDao;
import com.yonaxtics.gymwer.set.user.entity.User;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public  class   UserLogic  {


	public static boolean create(User user) {
		
		boolean result = false;	
		
		 
		if(user != null && !user.exists() && !user.isEmpty()){	 		    	  
		    		  
             result = UserDao.create(user);	    	
		}
	      
	    return result;
	}
	
	
	
	

	

	
	
}

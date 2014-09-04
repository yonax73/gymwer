package com.yonaxtics.gymwer.set.user.logic;



import com.yonaxtics.gymwer.set.person.entity.Person;
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
	
	public static boolean exists(User user){		
		boolean result = false;		
		if(user != null && !user.exists() && !user.getEmail().isEmpty()){			
			result = UserDao.exists(user);
		}		
		return result;
	}	
	
	public static boolean signIn(Person person){		
		boolean result = false;		
		if(person != null && !person.exists() && !person.getGym().getName().isEmpty() && 
		  !person.getUser().getEmail().isEmpty() && 
		  !person.getUser().getPassword().isEmpty()){			
			    result = UserDao.signIn(person);			
		}		
		return result;		
	}	
	
	public static boolean update(User user){
		boolean result = false;
		if(user != null && user.getId() > 0){
			if(user.getRole().getId() > 0 && user.getDefaultAction().getId() > 0){
				result = UserDao.update(user);	
			}			
		}
		return result;
	}
	
	
	
	

	

	
	
}

package com.yonaxtics.gymwer.set.user.logic;

import com.yonaxtics.gymwer.sec.Persitence;
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
		if(user != null && !user.exists()){		    		  
             result = UserDao.create(user);	    	
		}	      
	    return result;
	}
	
	public static boolean update(User user){
		boolean result = false;
		if(user != null && user.exists()){
			if(user.getRole().exists() && user.getDefaultAction().exists()){
				result = UserDao.update(user);	
			}			
		}
		return result;
	}



	/**
	 * @param user
	 * @return
	 */
	public static boolean relationalWithGym(User user) {
		boolean result = false;
		if(user!= null && user.exists() && user.getGym()!= null && user.getGym().exists()){
			result  = UserDao.relationalWithGym(user);
		}
		return result;
	}

	public static boolean loadByEmail(User user) {
		boolean result = false;
		if(user!= null && user.getLogin()!= null && !user.getLogin().isEmpty()){
			 result = UserDao.loadByEmail(user);
		}
		return result;
	}

	/**
	 * @param user
	 * @return
	 */
	public static boolean loadByLogin(User user) {
		boolean result = false;
		if(user != null && user.getLogin()!= null && user.getLogin().exists() && !user.getGym().isEmpty()){
			result = Persitence.find(user);
			if(!result){
				result = UserDao.loadByLogin(user);
				if(result){
					Persitence.setObject(user.getSerial(), user);	
				}				
			}
		}
		return result;
	}
	
	/**
	 * @param user
	 * @return
	 */
	public static boolean loadById(User user) {
		boolean result = false;
		if(user != null && user.getLogin()!= null && user.getLogin().exists() && !user.getGym().isEmpty()){
			result = UserDao.loadByLogin(user);
		}
		return result;
	}
	

	
	
	

	

	
	
}

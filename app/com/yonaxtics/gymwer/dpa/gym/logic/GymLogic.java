package com.yonaxtics.gymwer.dpa.gym.logic;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.dpa.gym.dao.*;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/26/2014)
 *
 */
public class GymLogic {

	
	public static boolean create(Gym gym) {
		
		boolean result = false;	
		
		 
		if(gym != null && !gym.exists() && !gym.getName().isEmpty()){	 		    	  
		    		  
               result =	GymDao.create(gym);
		}
	      
	    return result;
	}
	
	
	
	
	public static boolean signIn(Gym gym){
		
		boolean result = false;
		
		if(gym != null && !gym.exists() && !gym.getName().isEmpty() && 
		  !gym.getContact().getEmail().isEmpty() && 
		  !gym.getContact().getUser().getPassword().isEmpty()){
			
			    result = GymDao.signIn(gym);			
		}
		
		return result;		
	}
}

package com.yonaxtics.gymwer.dpa.gym.logic;

import com.yonaxtics.gymwer.dpa.gym.dao.GymDao;
import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
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

	/**
	 * @param gym
	 * @return
	 */
	public static boolean load(Gym gym) {
		boolean result = false;
		if(gym != null && gym.getId() > 0){
			result = GymDao.load(gym);
		}
		return result;
	}
	
	
	
	

	
	  

	     


	 
}

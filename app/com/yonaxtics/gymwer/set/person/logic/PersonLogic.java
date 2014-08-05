package com.yonaxtics.gymwer.set.person.logic;

import com.yonaxtics.gymwer.set.person.dao.PersonDao;
import com.yonaxtics.gymwer.set.person.entity.Person;


public class PersonLogic {

	
	public static boolean create(Person person) {
		
		boolean result = false;			
		 
		if(person != null && !person.exists() && !person.getEmail().isEmpty()){	 		    	  
		    		  
              result = PersonDao.create(person);
		}
	      
	    return result;
	}
	

	
	
	public static boolean exists(Person person){		
		
		boolean result = false;
		
		if(person != null && !person.exists() && !person.getEmail().isEmpty()){	 
			
			result = PersonDao.exists(person);
		}
		
		return result;
	}



	
}

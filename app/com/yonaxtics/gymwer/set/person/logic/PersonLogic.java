package com.yonaxtics.gymwer.set.person.logic;

import play.libs.F.Promise;

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
	
	public static Promise<Boolean> exists (Person person){
		
		return Promise.promise(() ->_exists(person));
		
	}
	
	
	private static boolean _exists(Person person){		
		
		boolean result = false;
		
		if(person != null && !person.exists() && !person.getEmail().isEmpty()){	 
			
			result = PersonDao.exists(person);
		}
		
		return result;
	}



	
}

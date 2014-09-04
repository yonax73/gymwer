package com.yonaxtics.gymwer.set.person.logic;

import com.yonaxtics.gymwer.set.person.dao.PersonDao;
import com.yonaxtics.gymwer.set.person.entity.Person;


public class PersonLogic {

	
	public static boolean create(Person person) {		
		boolean result = false;					 
		if(person != null && !person.exists()){	 		    		  
              result = PersonDao.create(person);
		}	      
	    return result;
	}	
	
	public static boolean loadProfile(Person contact){		
		boolean result = false;		
		if(contact != null && contact.exists()){			
			result = PersonDao.loadProfile(contact);
			contact.setId(0);
		}		
		return result;
	}


	/**
	 * @param contact
	 * @return
	 */
	public static boolean update(Person contact) {
		boolean result = false;
		if(contact!=null && contact.getId() >0){
			result = PersonDao.update(contact);
		}
		return result;
	}
	

	
	




	
}

package com.yonaxtics.gymwer.set.picture.logic;

import play.mvc.Http.MultipartFormData.FilePart;

import com.yonaxtics.gymwer.sec.session.Session;
import com.yonaxtics.gymwer.set.person.entity.Person;
import com.yonaxtics.gymwer.set.picture.dao.PictureDao;
import com.yonaxtics.gymwer.set.picture.entity.Picture;

/** 
 * Clase     : PictureLogic.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 26, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class PictureLogic {

	
	
	  public static boolean update(FilePart file){		  
		  boolean result = false;		  
		  if(file != null){		
			  Person contact = (Person) Session.getAttribute(Session.OK);
			  contact.setPicture(new Picture(file.getFile()));			  
			  contact.getPicture().setMime(file.getContentType());			  			  
			  result =  PictureDao.update(contact.getPicture());			  
		  }		  
		  return result;
	  }
}

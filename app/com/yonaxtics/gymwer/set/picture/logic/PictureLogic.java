package com.yonaxtics.gymwer.set.picture.logic;

import play.mvc.Http.MultipartFormData.FilePart;

import com.yonaxtics.gymwer.sec.securedController;
import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.set.picture.dao.PictureDao;

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
			  Login login = (Login) securedController.getAttribute(securedController.LOGIN);
			  if(login!=null){
				  login.getPerson().getPicture().setFile(file.getFile());			  
				  login.getPerson().getPicture().setMime(file.getContentType());			  			  
				  result =  PictureDao.update(login.getPerson().getPicture());	
			  }		  
		  }		  
		  return result;
	  }
}

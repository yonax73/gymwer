package com.yonaxtics.gymwer.set.picture.logic;

import static com.yonaxtics.gymwer.sec.Sec.dec;
import static com.yonaxtics.gymwer.util.Constant.SESSION_OK;
import com.yonaxtics.gymwer.set.picture.dao.*;
import static play.mvc.Controller.session;
import play.mvc.Http.MultipartFormData.FilePart;

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
			  Picture picture = new Picture(file.getFile());
			  picture.setMime(file.getContentType());
			  picture.setPersonId(Integer.parseInt(dec(session(SESSION_OK))));			  
			  result =  PictureDao.update(picture);
			  picture.setPersonId(0);
		  }		  
		  return result;
	  }
}

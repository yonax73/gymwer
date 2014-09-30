package com.yonaxtics.gymwer.set.picture.logic;

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

	
	
	  public static boolean update(Picture picture){		  
		  boolean result = false;		  
		  if(picture != null){				  			  
				  result =  PictureDao.update(picture);	
			  }		  		  
		  return result;
	  }
}

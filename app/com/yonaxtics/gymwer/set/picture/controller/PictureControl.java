package com.yonaxtics.gymwer.set.picture.controller;

import static com.yonaxtics.gymwer.util.Constant.REQUEST_SUCCESS;
import play.mvc.Controller;
import play.mvc.Result;

import com.yonaxtics.gymwer.set.picture.logic.PictureLogic;

/** 
 * Clase     : PictureControl.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 26, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class PictureControl extends Controller {
       
	
	public static Result uploadPicture(){
				
		if (PictureLogic.update( request().body().asMultipartFormData().getFile("picture"))) {		    
		    return ok(REQUEST_SUCCESS);
		}
		return ok("Internal Error 5001");	
	}

}

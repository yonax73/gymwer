package com.yonaxtics.gymwer.set.picture.controller;


import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;

import com.yonaxtics.gymwer.sec.Persitence;
import com.yonaxtics.gymwer.sec.SecuredController;
import com.yonaxtics.gymwer.set.picture.entity.Picture;
import com.yonaxtics.gymwer.set.picture.logic.PictureLogic;
import com.yonaxtics.gymwer.set.user.entity.User;

/** 
 * Clase     : PictureControl.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 26, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class PictureControl extends SecuredController {

	public static Result uploadPicture() {

		FilePart file = request().body().asMultipartFormData().getFile("picture");
		if (file != null) {
			User user = user_loggedIn();
			if (user != null) {
				Picture picture = user.getPicture();
				picture.setFile(file.getFile());
				picture.setMime(file.getContentType());
				if (PictureLogic.update(picture)) {
					Persitence.setObject(user.getSerial(), user);
					return authenticated(SUCCESS_REQUEST);
				} else {
					return authenticated("Error trying upload picture file!");
				}
			}else{
				return sign_out();
			}
		}else{
			return authenticated("This file is null");
		}
		
	}

}

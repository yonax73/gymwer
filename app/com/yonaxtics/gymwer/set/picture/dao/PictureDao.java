package com.yonaxtics.gymwer.set.picture.dao;

import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;

import play.Logger;
import play.db.DB;

import com.yonaxtics.gymwer.set.picture.entity.Picture;
import com.yonaxtics.gymwer.util.base.dao.Dao;

/** 
 * Clase     : PictureDao.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 26, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class PictureDao extends Dao {

	
	
	  public static boolean update(Picture picture){
		  
		    boolean result = false;
			CallableStatement cst = null;
			Connection conn = null;			
			try {				 
				 conn = DB.getConnection();			 
				 String sql = "CALL sp_set_pictures_UPDATE(?,?,?)";			 
				 cst = conn.prepareCall(sql);				 
				 cst.setInt(1,picture.getId());				 
				 cst.setString(2, picture.getMime());
				 cst.setBlob(3, new FileInputStream(picture.getFile()));				 
				 result = cst.executeUpdate() > 0;				 
			} catch (Exception e) {	              
				Logger.error(e.getMessage());				
			} finally{				
				if(cst != null) cst = null;
				close(conn);				
			}			
			return result;
	  }
}

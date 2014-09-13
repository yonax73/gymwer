package com.yonaxtics.gymwer.set.profile.dao;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Base64;

import play.Logger;
import play.db.DB;

import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.set.master.entity.Address;
import com.yonaxtics.gymwer.set.master.entity.Phone;
import com.yonaxtics.gymwer.set.person.entity.Person;
import com.yonaxtics.gymwer.set.picture.entity.Picture;
import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.util.base.dao.*;

/** 
 * Clase     : ProfileDao.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 4, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class ProfileDao extends Dao {

	public static boolean load(Person contact){		
		boolean result = false;		
		CallableStatement cst = null;
		ResultSet rs  = null;
		Connection conn = null;		
		try {			
			conn = DB.getConnection();
			String sql = "CALL sp_set_profile_LOAD(?)";
			cst = conn.prepareCall(sql);
			cst.setInt(1, contact.getId());			
			rs  = cst.executeQuery();				
			if(rs.next()){				
		        result = true;			      			    	  			    	  
    	        contact.setDocument(rs.getString(1));		        
    	        contact.setName(rs.getString(2));
    	        contact.setLocation(new Location(rs.getInt(3),new Phone(rs.getString(4)), new Address(rs.getString(5))));
    	        contact.setUser(new User(rs.getInt(6),rs.getString(7)));
    	        contact.getUser().setEmail(rs.getString(8));
    	        contact.getUser().setRole( new Role(rs.getString(9)));
    	        contact.getUser().setDefaultAction(new Action(rs.getInt(10)));
    	        if(rs.getBlob(11) != null){
	    	        Blob blob = rs.getBlob(11);	    	     	    	        
	    	        contact.setPicture(new Picture(Base64.getEncoder().encodeToString(blob.getBytes(1, (int) blob.length()))));
	    	        contact.getPicture().setMime(rs.getString(12));
    	        }
    	        contact.getUser().getRole().setId(rs.getInt(13));
    	        contact.getLocation().getPhone().setId(rs.getInt(14));
    	        contact.getLocation().getAddress().setId(rs.getInt(15));	
    	        contact.getPicture().setId(rs.getInt(16));
			}			
		} catch (Exception e) {			
			Logger.error(e.getMessage());			
		} finally{			
			if(cst != null) cst = null;
			close(conn);
		}		
		return result;		
	}
}

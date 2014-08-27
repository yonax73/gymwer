package com.yonaxtics.gymwer.set.person.dao;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import play.Logger;
import play.db.DB;

import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.set.file.entity.File;
import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.set.master.entity.Address;
import com.yonaxtics.gymwer.set.master.entity.Phone;
import com.yonaxtics.gymwer.set.person.entity.Person;
import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.util.base.dao.Dao;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/26/2014)
 *
 */
public class PersonDao extends Dao{
     
	
	
	public static boolean create(Person person){
		
		boolean result = false;
		
		CallableStatement cst = null; 
		Connection conn = null;
		
		try {
			
			conn = DB.getConnection();
			String sql = "CALL sp_set_person_CREATE(?, ?, ?, ?, ?, ?);";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);			

			cst.setInt(2, person.getUser().getId());
			
			if(person.getLocation() == null){
				
				person.setLocation(new Location(0));
			}
			cst.setInt(3, person.getLocation().getId());			
			
			cst.setString(4, person.getDocument());
			cst.setString(5, person.getName());	
			
			cst.setInt(6, person.getGym().getId());			
			
			result = cst.executeUpdate() > 0;
			
			if(result) person.setId(cst.getInt(1));
			
		} catch (Exception e) {
			
			Logger.error(e.getMessage());
			
		} finally{
			
			if(cst != null) cst = null;
			close(conn);
		}
		
		return result;		
	}
	
	
	
	public static boolean loadProfile(Person contact){
		
		boolean result = false;
		
		CallableStatement cst = null;
		ResultSet rs  = null;
		Connection conn = null;
		
		try {
			
			conn = DB.getConnection();
			String sql = "CALL sp_set_person_LOAD_PROFILE(?);";
			cst = conn.prepareCall(sql);
			cst.setInt(1, contact.getId());
			
			rs  = cst.executeQuery();	
			
			if(rs.next()){
				
			      result = true;
			      
			      do{			    	  
			    	  
	    	        contact.setDocument(rs.getString(1));
	    	        contact.setName(rs.getString(2));
	    	        contact.setLocation(new Location(new Phone(rs.getString(3)), new Address(4)));
	    	        contact.setUser(new User(rs.getString(5)));
	    	        contact.getUser().setEmail(rs.getString(6));
	    	        contact.getUser().setRole( new Role(rs.getString(7)));
	    	        contact.getUser().setDefaultAction(new Action(rs.getInt(8)));
	    	        contact.setPicture(new File(rs.getInt(9)));	    	        
	    	        Blob img = rs.getBlob(10);	    	     	    	        
	    	        contact.getPicture().setData(img.getBytes(1, (int) img.length()));	
	    	       
			    	  
			      }while(rs.next());				
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

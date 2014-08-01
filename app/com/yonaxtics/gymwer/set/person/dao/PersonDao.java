package com.yonaxtics.gymwer.set.person.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import play.db.DB;

import com.yonaxtics.gymwer.util.base.dao.Dao;
import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.set.master.entity.Role;
import com.yonaxtics.gymwer.set.person.entity.*;
import com.yonaxtics.gymwer.set.user.entity.User;
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
			String sql = "CALL sp_set_person_CREATE(?, ?, ?, ?, ?, ?, ?);";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);
			
			if(person.getUser() == null){
				
				person.setUser(new User(0));
			}			
			cst.setInt(2, person.getUser().getId());
			
			if(person.getLocation() == null){
				
				person.setLocation(new Location(0));
			}
			cst.setInt(3, person.getLocation().getId());
			
			if(person.getRole() == null){
				
				person.setRole(new Role(0));
			}			
			cst.setInt(4, person.getRole().getId());
			
			cst.setString(5, person.getDocument());
			cst.setString(6, person.getName());
			cst.setString(7, person.getEmail());			
			
			result = cst.executeUpdate() > 0;
			
			if(result) person.setId(cst.getInt(1));
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally{
			
			if(cst != null) cst = null;
			close(conn);
		}
		
		return result;		
	}

	
	
	public static boolean exists(Person person){
		
		boolean result = false;		
		CallableStatement cst = null;
		ResultSet rs  = null;
		Connection conn = null;
		
		try {
			
			conn = DB.getConnection();
			String sql = "CALL sp_set_user_EXISTS(?);";
			cst = conn.prepareCall(sql);
			
			cst.setString(1, person.getEmail());			
			
			rs  = cst.executeQuery();	
			
			if(rs.next()){
				
				result = rs.getInt(1) > 0;				
						
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally{
			
			if(cst != null) cst = null;
			close(conn);
		}
		
		return result;		
	}
}

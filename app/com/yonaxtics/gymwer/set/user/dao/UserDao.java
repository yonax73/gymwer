package com.yonaxtics.gymwer.set.user.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import play.Logger;
import play.db.DB;

import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.util.base.dao.Dao;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class UserDao extends Dao{
	
	public static boolean create(User user){		
		boolean result = false;		
		CallableStatement cst = null;
		Connection conn = null;		
		try {			 
			 conn = DB.getConnection();			 
			 String sql = "CALL sp_set_users_CREATE(?,?,?,?,?);";			 
			 cst = conn.prepareCall(sql);
			 cst.registerOutParameter(1, Types.INTEGER);
			 cst.setString(2, user.getName());
			 cst.setString(3, user.getPassword());			 
			 cst.setString(4, user.getEmail());
			 cst.setInt(5, user.getRole().getId());			 
			 result = cst.executeUpdate() > 0;			 
			 if(result) user.setId(cst.getInt(1));			 
		} catch (Exception e) {              
			Logger.error(e.getMessage());			
		} finally{			
			if(cst != null) cst = null;
			close(conn);			
		}		
		return result;		
	}	
	
	public static boolean exists(User user){		
		boolean result = false;		
		CallableStatement cst = null;
		ResultSet rs  = null;
		Connection conn = null;		
		try {			
			conn = DB.getConnection();
			String sql = "CALL sp_set_users_EXISTS(?);";
			cst = conn.prepareCall(sql);			
			cst.setString(1, user.getEmail());			
			rs  = cst.executeQuery();				
			if(rs.next()){				
				result = rs.getInt(1) > 0;							
			}						
		} catch (Exception e) {			
			Logger.error(e.getMessage());			
		} finally{			
			if(cst != null) cst = null;
			close(conn);
		}		
		return result;		
	}	
	

	
	public static boolean update(User user){
		boolean result = false;
		CallableStatement cst = null;		
		Connection conn = null;
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL sp_set_users_UPDATE(?,?,?,?,?)");
			cst.setInt(1,user.getId());
			cst.setInt(2,user.getRole().getId());
			cst.setInt(3, user.getDefaultAction().getId());
			cst.setString(4, user.getName());
			cst.setString(5, user.getEmail());
			result = cst.executeUpdate() > 0;
		} catch (Exception e) {
            Logger.error(e.getMessage());
		}finally{
			if(cst!=null) cst = null;
			close(conn);
		}
		return result;
	}
}

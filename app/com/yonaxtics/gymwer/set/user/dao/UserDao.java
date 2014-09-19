package com.yonaxtics.gymwer.set.user.dao;


import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Base64;

import play.Logger;
import play.db.DB;

import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.set.master.entity.Address;
import com.yonaxtics.gymwer.set.master.entity.Phone;
import com.yonaxtics.gymwer.set.picture.entity.Picture;
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
			 String sql = "CALL sp_set_users_CREATE(?,?,?,?,?,?,?);";			 
			 cst = conn.prepareCall(sql);
			 cst.registerOutParameter(1, Types.INTEGER);
			 cst.setInt(2, user.getRole().getId());
			 cst.setInt(3, user.getLogin().getId());			 
			 cst.setInt(4, user.getLocation()==null ? 0 :user.getLocation().getId());
			 cst.setString(5, user.getDocument());
			 cst.setString(6, user.getName());
			 cst.setString(7, user.getLastName());
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

	public static boolean loadByEmail(User user) {
		boolean result = false;		
		CallableStatement cst = null;
		ResultSet rs  = null;
		Connection conn = null;		
		try {			
			conn = DB.getConnection();
			String sql = "CALL sp_set_users_LOAD_USER_BY_EMAIL(?)";
			cst = conn.prepareCall(sql);
			cst.setString(1, user.getLogin().getEmail());			
			rs  = cst.executeQuery();				
		    result = rs.next();
		    if(result) user.setId(rs.getInt(1));
		    result = user.getId() > 0;
		} catch (Exception e) {			
			Logger.error(e.getMessage());			
		} finally{			
			if(cst != null) cst = null;
			close(conn);
		}		
		return result;				
	}

	public static boolean relationalWithGym(User user) {
		boolean result = false;		
		CallableStatement cst = null;
		Connection conn = null;		
		try {			 
			 conn = DB.getConnection();			 
			 String sql = "CALL sp_mst_users_gyms_CREATE(?,?,?)";			 
			 cst = conn.prepareCall(sql);
			 cst.registerOutParameter(1, Types.INTEGER);
			 cst.setInt(2, user.getId());
			 cst.setInt(3, user.getLogin().getId());			 
			 result = cst.executeUpdate() > 0 && cst.getInt(1) > 0;			 
		} catch (Exception e) {              
			Logger.error(e.getMessage());			
		} finally{			
			if(cst != null) cst = null;
			close(conn);			
		}		
		return result;		
	}
}

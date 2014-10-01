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
			cst = conn.prepareCall("CALL sp_set_users_UPDATE(?,?,?,?,?,?,?,?,?)");
			cst.setInt(1,user.getId());
			cst.setString(2, user.getDocument());
			cst.setString(3, user.getName());
			cst.setString(4, user.getLastName());
			cst.setInt(5, user.getLocation().getId());
			cst.setInt(6,user.getRole().getId());
			cst.setInt(7, user.getDefaultAction().getId());
			cst.setString(8, user.getLogin().getName());
			cst.setInt(9, user.getLogin().getId());
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
			String sql = "CALL sp_set_users_LOAD_BY_EMAIL(?)";
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
	
	public static boolean loadByLogin(User user){
		boolean result = false;		
		CallableStatement cst = null;
		ResultSet rs  = null;
		Connection conn = null;		
		try {			
			conn = DB.getConnection();
			String sql = "CALL sp_set_users_LOAD_BY_LOGIN(?,?)";
			cst = conn.prepareCall(sql);
			cst.setInt(1, user.getLogin().getId());
			cst.setString(2, user.getGym().getName());
			rs  = cst.executeQuery();
			if(rs.next()){
				user.setId(rs.getInt(1));
				user.setDocument(rs.getString(2));
				user.setName(rs.getString(3));
				user.setLastName(rs.getString(4));
				user.getGym().setId(rs.getInt(5));
				user.setRole(new Role(rs.getInt(6),rs.getString(7)));
				user.setDefaultAction(new Action(rs.getInt(8),rs.getString(9)));
				user.setLocation(new Location(rs.getInt(10)));
				if(user.getLocation().exists()){
					user.getLocation().setPhone(new Phone(rs.getInt(11), rs.getString(12)));
					user.getLocation().setAddress(new Address(rs.getInt(13), rs.getString(14)));
				}
				user.setPicture(new Picture(rs.getInt(15)));
				if(user.getPicture().exists()){
					user.getPicture().setMime(rs.getString(16));
				    Blob blob = rs.getBlob(17);	
					user.getPicture().setSrc(Base64.getEncoder().encodeToString(blob.getBytes(1, (int) blob.length())));
				}
				user.getLogin().setName(rs.getString(18));
			}
			result = user.exists();
		    
		} catch (Exception e) {			
			Logger.error(e.getMessage());			
		} finally{			
			if(cst != null) cst = null;
			close(conn);
		}		
		return result;		
	}
	
	
	public static boolean loadById(User user){
		boolean result = false;		
		CallableStatement cst = null;
		ResultSet rs  = null;
		Connection conn = null;		
		try {			
			conn = DB.getConnection();
			String sql = "CALL sp_set_users_LOAD_BY_ID(?)";
			cst = conn.prepareCall(sql);
			cst.setInt(1, user.getId());			
			rs  = cst.executeQuery();
			if(rs.next()){
				user.setId(rs.getInt(1));
				user.setDocument(rs.getString(2));
				user.setName(rs.getString(3));
				user.setLastName(rs.getString(4));
				user.getGym().setId(rs.getInt(5));
				user.setRole(new Role(rs.getInt(6),rs.getString(7)));
				user.setDefaultAction(new Action(rs.getInt(8),rs.getString(9)));
				user.setLocation(new Location(rs.getInt(10)));
				if(user.getLocation().exists()){
					user.getLocation().setPhone(new Phone(rs.getInt(11), rs.getString(12)));
					user.getLocation().setAddress(new Address(rs.getInt(13), rs.getString(14)));
				}
				user.setPicture(new Picture(rs.getInt(15)));
				if(user.getPicture().exists()){
					user.getPicture().setMime(rs.getString(16));
				    Blob blob = rs.getBlob(17);	
					user.getPicture().setSrc(Base64.getEncoder().encodeToString(blob.getBytes(1, (int) blob.length())));
				}
			}
			result = user.exists();
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
			 cst.setInt(3, user.getGym().getId());			 
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

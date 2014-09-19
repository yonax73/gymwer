package com.yonaxtics.gymwer.dpa.gym.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import play.Logger;
import play.db.DB;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.set.master.entity.Address;
import com.yonaxtics.gymwer.set.master.entity.Phone;
import com.yonaxtics.gymwer.util.base.dao.Dao;
/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/26/2014)
 *
 */
public class GymDao extends Dao{

	
	public static boolean create(Gym gym){		
		boolean result = false;		
		CallableStatement cst = null; 
		Connection conn = null;		
		try {			
			conn = DB.getConnection();
			String sql = "CALL sp_dpa_gyms_CREATE(?, ?);";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);			
			cst.setString(2, gym.getName());						
			result = cst.executeUpdate() > 0;			
			if(result) gym.setId(cst.getInt(1));			
		} catch (Exception e) {			
			Logger.error(e.getMessage());			
		} finally{			
			if(cst != null) cst = null;
			close(conn);
		}		
		return result;		
	}

	/**
	 * @param gym
	 * @return
	 */
	public static boolean load(Gym gym) {
		boolean result = false;		
		CallableStatement cst = null;
		ResultSet rs  = null;
		Connection conn = null;		
		try {			
			conn = DB.getConnection();
			String sql = "CALL sp_dpa_gyms_LOAD(?)";
			cst = conn.prepareCall(sql);
			cst.setInt(1, gym.getId());			
			rs  = cst.executeQuery();				
			if(rs.next()){				
			      result = true;
			      gym.setNit(rs.getString(1));
			      gym.setName(rs.getString(2));
			      gym.setLocation(new Location(rs.getInt(3)));
			      gym.getLocation().setPhone(new Phone(rs.getInt(4),rs.getString(5)));
			      gym.getLocation().setAddress(new Address(rs.getInt(6),rs.getString(7)));			      				
			}			
		} catch (Exception e) {			
			Logger.error(e.getMessage());			
		} finally{			
			if(cst != null) cst = null;
			close(conn);
		}		
		return result;		
	}

	/**
	 * @param gym
	 * @return
	 */
	public static boolean update(Gym gym) {
		boolean result = false;
		CallableStatement cst = null;		
		Connection conn = null;
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL sp_dpa_gyms_UPDATE(?,?,?,?)");
			cst.setInt(1, gym.getId());
			cst.setString(2, gym.getNit());
			cst.setString(3, gym.getName());
			cst.setInt(4, gym.getLocation() == null ? 0 : gym.getLocation().getId());
			result = cst.executeUpdate() > 0;
		} catch (Exception e) {
            Logger.error(e.getMessage());
		}finally{
			if(cst!=null) cst = null;
			close(conn);
		}
		return result;
	}

	public static boolean exists(Gym gym) {
		boolean result = false;		
		CallableStatement cst = null;
		ResultSet rs  = null;
		Connection conn = null;		
		try {			
			conn = DB.getConnection();
			String sql = "CALL sp_dpa_gym_EXISTS(?);";
			cst = conn.prepareCall(sql);			
			cst.setString(1, gym.getName());			
			rs  = cst.executeQuery();			
			result = rs.next() && rs.getInt(1) > 0;									
		} catch (Exception e) {			
			Logger.error(e.getMessage());			
		} finally{			
			if(cst != null) cst = null;
			close(conn);
		}		
		return result;		
	}

	
	
	
	

	
	
}

package com.yonaxtics.gymwer.set.location.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import play.Logger;
import play.db.DB;

import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.util.base.dao.Dao;

/** 
 * Clase     : LocationDao.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 3, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class LocationDao extends Dao {

	/**
	 * @param location
	 * @return
	 */
	public static boolean update(Location location) {
		boolean result = false;
		CallableStatement cst = null;		
		Connection conn = null;
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL sp_set_locations_UPDATE(?,?,?)");
			cst.setInt(1,location.getId());
			cst.setInt(2, location.getPhone() == null ? 0 :location.getPhone().getId());
			cst.setInt(3, location.getAddress() == null ? 0 :location.getAddress().getId());			
			result = cst.executeUpdate() > 0;
		} catch (Exception e) {
            Logger.error(e.getMessage());
		}finally{
			if(cst!=null) cst = null;
			close(conn);
		}
		return result;		
	}

	/**
	 * @param location
	 * @return
	 */
	public static boolean create(Location location) {
		boolean result = false;		
		CallableStatement cst = null;
		Connection conn = null;		
		try {			 
			 conn = DB.getConnection();			 
			 String sql = "CALL sp_set_locations_CREATE(?,?,?);";			 
			 cst = conn.prepareCall(sql);
			 cst.registerOutParameter(1, Types.INTEGER);
			 cst.setInt(2, location.getPhone() == null ? 0 :location.getPhone().getId());
			 cst.setInt(3, location.getAddress() == null ? 0 :location.getAddress().getId());		 
			 result = cst.executeUpdate() > 0;			 
			 if(result) location.setId(cst.getInt(1));			 
		} catch (Exception e) {              
			Logger.error(e.getMessage());			
		} finally{			
			if(cst != null) cst = null;
			close(conn);			
		}		
		return result;
	}

}

package com.yonaxtics.gymwer.set.master.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import play.Logger;
import play.db.DB;

import com.yonaxtics.gymwer.set.master.entity.MasterValue;

import  com.yonaxtics.gymwer.util.base.dao.Dao;

/** 
 * Clase     : MasterDao.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 2, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class MasterDao extends Dao {

	/**
	 * @param masterValue
	 * @return
	 */
	public static boolean update(MasterValue masterValue)  {
		boolean result = false;
		CallableStatement cst = null;		
		Connection conn = null;
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL t_set_master_values_UPDATE(?,?,?,?)");
			cst.setInt(1,masterValue.getId());
			cst.setString(2, masterValue.getValue1());
			cst.setString(3, masterValue.getValue2());
			cst.setString(4, masterValue.getValue3());
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
	 * @param masterValue
	 * @return
	 */
	public static boolean create(MasterValue masterValue) {
		boolean result = false;		
		CallableStatement cst = null;
		Connection conn = null;		
		try {			 
			 conn = DB.getConnection();			 
			 String sql = "CALL t_set_master_values_CREATE(?,?,?,?,?);";			 
			 cst = conn.prepareCall(sql);
			 cst.registerOutParameter(1, Types.INTEGER);
			 cst.setInt(2,masterValue.getMasterId());	
			 cst.setString(3, masterValue.getValue1());
			 cst.setString(4, masterValue.getValue2());
			 cst.setString(5, masterValue.getValue3());
			 result = cst.executeUpdate() > 0;			 
			 if(result) masterValue.setId(cst.getInt(1));			 
		} catch (Exception e) {              
			Logger.error(e.getMessage());			
		} finally{			
			if(cst != null) cst = null;
			close(conn);			
		}		
		return result;
	}
	
	
}

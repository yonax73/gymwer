package com.yonaxtics.gymwer.sec.login.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import play.Logger;
import play.db.DB;

import com.yonaxtics.gymwer.sec.login.entity.Login;
import com.yonaxtics.gymwer.util.base.dao.Dao;
/** 
 * Clase     : LoginDao.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 4, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class LoginDao extends Dao{
	
	public static boolean create(Login login){		
		boolean result = false;		
		CallableStatement cst = null;
		Connection conn = null;		
		try {			 
			 conn = DB.getConnection();			 
			 String sql = "CALL sp_sec_login_CREATE(?,?,?,?);";			 
			 cst = conn.prepareCall(sql);
			 cst.registerOutParameter(1, Types.INTEGER);
			 cst.setString(2, login.getName());
			 cst.setString(3, login.getPassword());			 
			 cst.setString(4, login.getEmail());				 
			 result = cst.executeUpdate() > 0;			 
			 if(result) login.setId(cst.getInt(1));			 
		} catch (Exception e) {              
			Logger.error(e.getMessage());			
		} finally{			
			if(cst != null) cst = null;
			close(conn);			
		}		
		return result;		
	}	
	
	public static boolean signIn(Login login, String nameGym) {		
		boolean result = false;		
		CallableStatement cst = null;
		ResultSet rs  = null;
		Connection conn = null;		
		try {			
			conn = DB.getConnection();
			String sql = "CALL sp_sec_login_SIGN_IN(?,?,?);";
			cst = conn.prepareCall(sql);			
			cst.setString(1, nameGym);
			cst.setString(2, login.getEmail());
			cst.setString(3, login.getPassword());			
			rs  = cst.executeQuery();							
			result = rs.next() &&  rs.getInt(1) == 1;			
		} catch (Exception e) {			
			Logger.error(e.getMessage());			
		} finally{			
			if(cst != null) cst = null;
			close(conn);
		}		
		return result;		
	}	
	
	public static boolean exists(Login login){		
		boolean result = false;		
		CallableStatement cst = null;
		ResultSet rs  = null;
		Connection conn = null;		
		try {			
			conn = DB.getConnection();
			String sql = "CALL sp_sec_login_EXISTS(?);";
			cst = conn.prepareCall(sql);			
			cst.setString(1, login.getEmail());			
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

package com.yonaxtics.gymwer.sec.login.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import play.Logger;
import play.db.DB;

import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.sec.login.entity.*;
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
	public static boolean signIn(Login login) {		
		boolean result = false;		
		CallableStatement cst = null;
		ResultSet rs  = null;
		Connection conn = null;		
		try {			
			conn = DB.getConnection();
			String sql = "CALL sp_sec_login_SIGN_IN(?,?,?);";
			cst = conn.prepareCall(sql);			
			cst.setString(1, login.getPerson().getGym().getName());
			cst.setString(2, login.getPerson().getUser().getEmail());
			cst.setString(3, login.getPerson().getUser().getPassword());			
			rs  = cst.executeQuery();				
			if(rs.next()){				
				result = rs.getInt(1) == 1;				
				if(result) {					
					login.getPerson().setId(rs.getInt(2));
					login.getPerson().getUser().setName(rs.getString(3));
					login.getPerson().getUser().setDefaultAction(new Action(rs.getString(4)));		
					login.getPerson().getGym().setId(rs.getInt(5));
				}
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

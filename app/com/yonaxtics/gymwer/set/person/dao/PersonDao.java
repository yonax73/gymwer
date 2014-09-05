package com.yonaxtics.gymwer.set.person.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import play.Logger;
import play.db.DB;

import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.set.person.entity.Person;
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
			String sql = "CALL sp_set_persons_CREATE(?, ?, ?, ?, ?, ?);";
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

	/**
	 * @param contact
	 * @return
	 */
	public static boolean update(Person contact) {
		boolean result = false;
		CallableStatement cst = null;		
		Connection conn = null;
		try {
			conn = DB.getConnection();
			cst = conn.prepareCall("CALL sp_set_persons_UPDATE(?,?,?,?)");
			cst.setInt(1, contact.getId());
			cst.setString(2, contact.getDocument());
			cst.setString(3, contact.getName());
			cst.setInt(4, contact.getLocation() == null ? 0 : contact.getLocation().getId());
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

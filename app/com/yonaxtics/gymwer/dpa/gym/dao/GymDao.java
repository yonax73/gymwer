package com.yonaxtics.gymwer.dpa.gym.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import play.db.DB;

import com.yonaxtics.gymwer.dpa.gym.entity.Gym;
import com.yonaxtics.gymwer.set.location.entity.Location;
import com.yonaxtics.gymwer.set.person.entity.Person;
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
			String sql = "CALL sp_dpa_gym_CREATE(?, ?, ?, ?, ?);";
			cst = conn.prepareCall(sql);
			cst.registerOutParameter(1, Types.INTEGER);		
			
			cst.setString(2, gym.getNit());
			cst.setString(3, gym.getName());
			
			if(gym.getContact()== null){
				
				gym.setContact(new Person(0));
			}			
			cst.setInt(4, gym.getContact().getId());
			
			if(gym.getLocation()== null){
				
				gym.setLocation(new Location(0));
			}
			cst.setInt(5, gym.getLocation().getId());			
			
			result = cst.executeUpdate() > 0;
			
			if(result) gym.setId(cst.getInt(1));
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally{
			
			if(cst != null) cst = null;
			close(conn);
		}
		
		return result;		
	}

	
	
	
	
	public static boolean signIn(Gym gym) {
		

	}
	
	
}

package com.yonaxtics.gymwer.util.base.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/26/2014)
 *
 */
public class Dao {

	
	

	public static  void close(Connection connection){
		
	    try {
	    	
			connection.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}

package com.yonaxtics.gymwer.util.list.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import play.Logger;
import play.db.DB;

import com.yonaxtics.gymwer.util.base.dao.Dao;
import com.yonaxtics.gymwer.util.list.entity.ListItem;
import com.yonaxtics.gymwer.util.list.entity.item.Item;

/** 
 * Clase     : ListDao.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 29, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class ListDao extends Dao {

	/**
	 * @param urls
	 * @return
	 */
	public static boolean loadPersonUrls(ListItem urls) {
		
		boolean result = false;		
		CallableStatement cst = null;
		ResultSet rs  = null;
		Connection conn = null;		
		try {			
			conn = DB.getConnection();
			String sql = "CALL sp_set_actions_LIST_URLS_BY_PERSON(?)";
			cst = conn.prepareCall(sql);
			cst.setInt(1, urls.getEntity().getId());			
			rs  = cst.executeQuery();				
			if(rs.next()){				  	      
			      do{			    	  
			    	 urls.add(new Item(rs.getInt(1), rs.getString(2)));			    	  
			      }while(rs.next());				
			}			
			result =  urls.size() > 0;			
		} catch (Exception e) {			
			Logger.error(e.getMessage());			
		} finally{			
			if(cst != null) cst = null;
			close(conn);
		}		
		return result;
	}

}

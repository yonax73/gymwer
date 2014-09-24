package com.yonaxtics.gymwer.set.permission.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import play.Logger;
import play.db.DB;

import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.set.master.entity.ActionType;
import com.yonaxtics.gymwer.set.module.entity.Module;
import com.yonaxtics.gymwer.set.permission.entity.Permission;
import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.util.base.dao.Dao;


/** 
 * Class     : PermissionDao.java.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 15, 2014<br/> 
 * User      : YQ<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class PermissionDao extends Dao {

	/**
	 * @param contact
	 * @return
	 */
	public static boolean loadNav(User user) {		
		boolean result = false;		
		CallableStatement cst = null;
		ResultSet rs  = null;
		Connection conn = null;		
		try {			
			conn = DB.getConnection();
			String sql = "CALL sp_set_permissions_LOAD_NAV(?,?,?);";
			cst = conn.prepareCall(sql);
			cst.setInt(1, user.getId());
			cst.setInt(2, ActionType.LOAD);
			cst.setInt(3, ActionType.SHOW);
			rs  = cst.executeQuery();				
			if(rs.next()){				
			      result = true;	
			      user.setRole(new Role(0));
			      user.getRole().setPermissions(new ArrayList<Permission>());			      
			      do{			    	  			    	  
			    	Action action = new Action(rs.getInt(1));
			    	action.setUrl(rs.getString(2));
			    	action.setIco(rs.getString(3));
			    	action.setModule(new Module(rs.getInt(4),rs.getString(5),rs.getInt(6)));			    	
			    	if(action.getId() == Action.LOAD_USER){			    		
			    		action.getModule().setDescription(user.getName());			    		
			    	} else if (action.getId() == Action.LOAD_GYM){			    		
			    		action.getModule().setDescription(user.getGym().getName());			    		
			    	}			    	
			    	if(action.getModule().isChild()){			    		
			    		action.getModule().setParent(new Module(rs.getInt(7),rs.getString(8)));			    		
			    	}			    	
					user.getRole().getPermissions().add(new Permission(action));			    	  
			      }while(rs.next());				
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

package com.yonaxtics.gymwer.set.master.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import play.Logger;
import play.db.DB;






import com.yonaxtics.gymwer.set.master.entity.MasterValue;
import com.yonaxtics.gymwer.util.base.dao.Dao;

/** 
 * Clase     : MasterValueDao.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 12, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class MasterValueDao extends Dao {
         
	
	public static Stream<MasterValue> loadAll(int masterId){
		
		List<MasterValue> masterValueList = null;
		CallableStatement cst = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			
			conn = DB.getConnection();
			
			String sql = "CALL sp_set_master_value_LOAD(?)";
			cst = conn.prepareCall(sql);
			
			cst.setInt(1, masterId);
			
			rs = cst.executeQuery();
			
			if(rs.next()){			
				
				masterValueList = new ArrayList<MasterValue>();
				
				do {
					
					MasterValue masterValue = new MasterValue(rs.getInt(1));
					masterValue.setValue1(rs.getString(2));
					masterValue.setValue2(rs.getString(3));
					masterValue.setValue3(rs.getString(4));
					masterValue.setKeyId(rs.getInt(5));
					masterValue.setData1(rs.getInt(6));
					
					masterValueList.add(masterValue);
					
				} while (rs.next());
			}
			
			
		} catch (Exception e) {
			
			Logger.error(e.getMessage());
			
		} finally {
			
			if(cst != null) cst = null;
			close(conn);
			
		}		
		
		return masterValueList.stream();
	}
	
}

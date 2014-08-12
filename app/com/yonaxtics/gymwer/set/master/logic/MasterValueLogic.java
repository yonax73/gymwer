package com.yonaxtics.gymwer.set.master.logic;

import java.util.stream.Stream;

import com.yonaxtics.gymwer.set.master.dao.MasterValueDao;
import com.yonaxtics.gymwer.set.master.entity.MasterValue;

/** 
 * Clase     : MasterValueLogic.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 12, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class MasterValueLogic {
        
	
	
	
	public static Stream<MasterValue> loadAll(MasterValue masterValue){
		
		Stream<MasterValue>  masterValueStream = null;
		
		if(masterValue != null && masterValue.exists()){
			
			masterValueStream = MasterValueDao.loadAll(masterValue.getId());
		}		
		
		return masterValueStream;
	}
}

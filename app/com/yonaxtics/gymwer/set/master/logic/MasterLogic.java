package com.yonaxtics.gymwer.set.master.logic;

import com.yonaxtics.gymwer.set.master.dao.MasterDao;
import com.yonaxtics.gymwer.set.master.entity.MasterValue;

/** 
 * Clase     : MasterLogic.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 2, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class MasterLogic {
        
	public static boolean save(MasterValue masterValue){
		boolean result = false;
		if(masterValue!=null){
			if(masterValue.getId() >0){
				result = MasterDao.update(masterValue);
			}else {
				result = MasterDao.create(masterValue);
			}
		}
		return result;		
	}
}

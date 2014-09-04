package com.yonaxtics.gymwer.set.location.logic;

import com.yonaxtics.gymwer.set.location.dao.LocationDao;
import com.yonaxtics.gymwer.set.location.entity.Location;

/** 
 * Clase     : LocationLogic.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 3, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class LocationLogic {
      
	public static boolean save(Location location){
		boolean result = false;
		  if(location!=null){			  
				  if(location.getId() > 0){
                        result=LocationDao.update(location);
				  }else{
					  result=LocationDao.create(location);
				  }				  
			  }		  
		return result;
	}
}

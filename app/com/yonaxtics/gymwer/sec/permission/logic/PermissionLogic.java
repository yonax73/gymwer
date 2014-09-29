package com.yonaxtics.gymwer.sec.permission.logic;

import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.sec.Persitence;
import com.yonaxtics.gymwer.sec.permission.dao.PermissionDao;
import com.yonaxtics.gymwer.set.master.entity.ActionType;

/**
 * Class : PermissionLogic.java.java<br/>
 * Copyright : (c) 2014<br/>
 * Company : yonaxtics<br/>
 * date : Aug 15, 2014<br/>
 * User : YQ<br/>
 * 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class PermissionLogic {

	/**
	 * @param contact
	 */
	public static boolean load(Role role) {
		boolean result = false;
		if (role.exists()) {
			result = Persitence.find(role);
			if (!result) {
				if (role.isSuperAdmin()) {
					result = PermissionDao.load(role, ActionType.LOAD);
				} else {
					result = PermissionDao.load(role);
				}
				Persitence.setObject(role.getSerial(), role);
			}
			if (result) {
				role.addChildrenToParentsModules();
			}
		}

		return result;
	}

}

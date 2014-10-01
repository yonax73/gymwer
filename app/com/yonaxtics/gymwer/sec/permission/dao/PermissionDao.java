package com.yonaxtics.gymwer.sec.permission.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import play.Logger;
import play.db.DB;

import com.yonaxtics.gymwer.dpa.role.entity.Role;
import com.yonaxtics.gymwer.sec.permission.entity.Permission;
import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.set.master.entity.ActionType;
import com.yonaxtics.gymwer.set.master.entity.ModuleType;
import com.yonaxtics.gymwer.set.module.entity.Module;
import com.yonaxtics.gymwer.set.user.entity.User;
import com.yonaxtics.gymwer.util.base.dao.Dao;

/**
 * Class : PermissionDao.java.java<br/>
 * Copyright : (c) 2014<br/>
 * Company : yonaxtics<br/>
 * date : Aug 15, 2014<br/>
 * User : YQ<br/>
 * 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class PermissionDao extends Dao {

	/**
	 * @param contact
	 * @return
	 */
	public static boolean load(User user) {
		boolean result = false;
		CallableStatement cst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DB.getConnection();
			String sql = "CALL sp_sec_permissions_LOAD(?);";
			cst = conn.prepareCall(sql);
			cst.setInt(1, user.getId());

			rs = cst.executeQuery();
			if (rs.next()) {
				result = true;
				Role role = user.getRole();
				role.initPermissions();
				List<Permission> permissionsLoad = role.getPermissionsLoad();
				List<Permission> permissionsCreate = role.getPermissionsCreate();
				List<Permission> permissionsUpdate = role.getPermissionsUpdate();
				List<Permission> permissionsDelete = role.getPermissionsDelete();
				List<Permission> permissionsChangeStatus = role.getPermissionsChangeStatus();
				do {
					Action action = new Action(rs.getInt(1));
					action.setUrl(rs.getString(2));
					action.setIco(rs.getString(3));
					action.setModule(new Module(rs.getInt(4), rs.getString(5), new ModuleType(rs.getInt(6))));
					if (action.getModule().getModuleType().isChild()) {
						action.getModule().setParent(new Module(rs.getInt(7), rs.getString(8)));
					}
					action.setActionType(new ActionType(rs.getInt(9)));
					switch (action.getActionType().getId()) {
					case ActionType.CHANGE_STATUS:
						permissionsChangeStatus.add(new Permission(action));
						break;
					case ActionType.CREATE:
						permissionsCreate.add(new Permission(action));
						break;
					case ActionType.DELETE:
						permissionsDelete.add(new Permission(action));
						break;
					case ActionType.LOAD:
						final int id = action.getId();
						if (id == Action.LOAD_USER) {
							action.getModule().setDescription(user.getLogin().getName());
						} else if (id == Action.LOAD_GYM) {							
							action.getModule().setDescription(user.getGym().getName());
						}
						permissionsLoad.add(new Permission(action));
						break;
					case ActionType.UPDATE:
						permissionsUpdate.add(new Permission(action));
						break;
					}
				} while (rs.next());
			}
		} catch (Exception e) {
			Logger.error(e.getMessage());
		} finally {
			if (cst != null)
				cst = null;
			close(conn);
		}
		return result;
	}

}

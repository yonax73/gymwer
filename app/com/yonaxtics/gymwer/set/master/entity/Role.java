package com.yonaxtics.gymwer.set.master.entity;

import java.util.List;

import com.yonaxtics.gymwer.set.permission.entity.*;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class Role extends MasterValue {

	
	private List<Permission> permissionList;
	
	public Role(int id) {
		
		super(id);
		
	}
	
	public Role(int id, String name){
		
		super(id);
		setValue1(name);
	}
	
	public String getName(){
		
		return getValue1();
	}
	
	public void setName(String name){
		
		setValue1(name);
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	
}

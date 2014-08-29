package com.yonaxtics.gymwer.dpa.role.entity;

import java.util.List;
import java.util.stream.Collectors;

import play.Logger;

import com.yonaxtics.gymwer.set.action.entity.Action;
import com.yonaxtics.gymwer.set.master.entity.MasterValue;
import com.yonaxtics.gymwer.set.permission.entity.*;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (7/16/2014)
 *
 */
public class Role extends MasterValue {

	
	private List<Permission> permissions;
	
	public Role(int id) {
		
		super(id);
		
	}
	
	public Role(int id, String name){
		
		super(id);
		setValue1(name);
	}
	
	
	
	public Role(String name) {
		
		super(0);
		setValue1(name);
	}

	public void arrange(){			
		try{	
			  List<Action> actParents = getPermissions().stream().map(Permission::getAction).filter(act->act.getModule().isParent()).collect(Collectors.toList()); 	  
		      if(actParents.size() > 0){				
				List<Action> actChildren = getPermissions().stream().map(Permission::getAction).filter(act->act.getModule().isChild()).collect(Collectors.toList());				
				actChildren.stream().parallel().forEach(child -> {			
			      actParents.stream().parallel().forEach(parent->{				
				    if(child.getModule().getParent().equals(parent.getModule())){					
						child.getModule().releaseParent();				    		
						parent.getModule().getChildren().add(child);				    		
					} 
				  });
				});
			  }		    
		      getPermissions().removeIf(p -> p.getAction().getModule().isChild());	
		}catch(Exception e){
			Logger.error(e.getMessage());
		}
      
	}
	
	public String getName(){
		
		return getValue1();
	}
	
	public void setName(String name){
		
		setValue1(name);
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	
}

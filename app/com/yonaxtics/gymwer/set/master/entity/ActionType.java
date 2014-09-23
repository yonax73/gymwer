package com.yonaxtics.gymwer.set.master.entity;

@SuppressWarnings("serial")
public class ActionType extends MasterValue {

	public final static int LOAD = 49;
	public final static int SHOW = 50;
	
	public ActionType(int id) {
		super(id);		
	}
	
	public boolean isToNavigation(){
		return id == LOAD || id == SHOW;
	}

}

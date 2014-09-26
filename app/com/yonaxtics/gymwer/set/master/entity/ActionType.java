package com.yonaxtics.gymwer.set.master.entity;

@SuppressWarnings("serial")
public class ActionType extends MasterValue {

	public final static int LOAD = 49;
	public final static int CREATE = 51;
	public final static int UPDATE = 52;
	public final static int CHANGE_STATUS = 53;
	public final static int DELETE = 54;	
	
	public ActionType(int id) {
		super(id);		
	}
}

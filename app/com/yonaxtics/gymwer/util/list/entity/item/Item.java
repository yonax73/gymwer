package com.yonaxtics.gymwer.util.list.entity.item;

import java.io.Serializable;


/** 
 * Clase     : Item.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 29, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

@SuppressWarnings("serial")
public class Item implements Serializable{
	
	private int option;
	private String value;
	
	public Item(int option, String value){		
		this.option = option;
		this.value = value;
	}	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getOption() {
		return option;
	}
	public void setOption(int option) {
		this.option = option;
	}
}

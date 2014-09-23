package com.yonaxtics.gymwer.sec.navegation;

import java.util.List;

import com.yonaxtics.gymwer.set.action.entity.Action;

/** 
 * Class     : Item.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 22, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

@SuppressWarnings("serial")
public class Item extends Action{

	private List<Item> items;
	
	/**
	 * @param id
	 * @param url
	 */
	public Item(int id, String url) {
		super(id, url);
		
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	

	
}

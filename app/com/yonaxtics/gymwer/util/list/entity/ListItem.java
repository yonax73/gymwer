package com.yonaxtics.gymwer.util.list.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yonaxtics.gymwer.util.base.entity.Entity;
import com.yonaxtics.gymwer.util.list.entity.item.Item;

/** 
 * Clase     : ListItem.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 29, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class ListItem implements Serializable{	

	private static final long serialVersionUID = 1L;
	
	private Entity entity;
	private List<Item> items;
	
	public ListItem(){	
		this.items = new ArrayList<Item>();
	}	
	public ListItem(List<Item> items){
		this.items = items;
	}		
	public ListItem(Entity entity){
		this.entity = entity;
		this.items = new ArrayList<Item>();
	}	
	public void copy(ListItem listItem) {
		this.items = listItem.items;		
	}
	public void add(Item item){
		this.items.add(item);
	}
	public int size(){
		return this.items.size();
	}	
	public boolean isEmpty(){
		return this.items.isEmpty();
	}
	public String getSerial(){
	   return new String(String.valueOf(this.getClass().hashCode()).concat(":").concat(String.valueOf(entity.getId())));	
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}

}

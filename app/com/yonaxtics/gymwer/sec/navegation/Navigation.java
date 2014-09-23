package com.yonaxtics.gymwer.sec.navegation;

import java.util.ArrayList;
import java.util.List;

import com.yonaxtics.gymwer.set.action.entity.Action;


/** 
 * Class     : Navigation.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Sep 22, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class Navigation {
      
    private List<Item> items;
      
    public Navigation(){
    	items = new ArrayList<Item>();
    }
    
	public void add(Action action){
		items.add((Item) action);
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}



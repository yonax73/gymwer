package com.yonaxtics.gymwer.set.master.entity;

/**
 * 
 * @author yonatan quintero
 * @version 0.1 (8/13/2014)
 *
 */
public class Path extends MasterValue {
	
	
	

	public Path(int id) {
		super(id);
		
	}
	
	
	
	
	public String getName(){
		
		return getValue1();
		
	}
		
	
	public void setName(String name){
		
		setValue1(name);
		
	}
	
	
	public String getUrl(){
		
		return getValue2();
	}
	
	
	public void setUrl(String url){
		
		setValue2(url);
	}
	
	
	public String getIco(){
		
		return getValue3();
	}
	
	
	public void setIco(String ico){
		
		setValue3(ico);
	}
	
	
	public boolean isParent(){
		
		return getData1() == 1;
	}
		
	
	public int getParentId(){
		
		return getKeyId();
	}
	
	
	public boolean isChild(){
		
		return hasKey();
	}
	
	



}

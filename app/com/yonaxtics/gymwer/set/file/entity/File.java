package com.yonaxtics.gymwer.set.file.entity;

import com.yonaxtics.gymwer.util.base.entity.Entity;

/** 
 * Clase     : File.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 25, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

public class File extends Entity {

	private String mime;
	private byte[] data;
	
	/**
	 * @param id
	 */
	public File(int id) {
		super(id);		
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}



}

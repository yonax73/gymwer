package com.yonaxtics.gymwer.set.picture.entity;

import java.io.File;

import com.yonaxtics.gymwer.util.base.entity.Entity;

/** 
 * Clase     : File.java<br/>
 * Copyright : (c) 2014<br/>
 * Company   : yonaxtics<br/>
 * date      : Aug 25, 2014<br/> 
 * User      : yonatan<br/> 
 * @author Yonatan Alexis Quintero Rodriguez<br/>
 */

@SuppressWarnings("serial")
public class Picture extends Entity {

	private String mime;
	private String src;
	private File file;
	
	/**
	 * @param id
	 */
	public Picture(int id) {
		super(id);		
	}
	
	
	public Picture(File file){
		super(0);
		this.file = file;
	}
	
	public Picture(String src){
		super(0);
		this.src = src;
	}
	
	@Override
	public boolean isEmpty() {	
		return file == null;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}


	public String getSrc() {
		
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}



	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}



	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}





}

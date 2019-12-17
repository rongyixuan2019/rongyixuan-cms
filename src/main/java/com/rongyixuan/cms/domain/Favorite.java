/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: Favorite.java 
 * @Prject: rongyixuan-cms
 * @Package: com.rongyixuan.cms.domain 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月27日 上午8:56:26 
 * @version: V1.0   
 */
package com.rongyixuan.cms.domain;

import java.io.Serializable;

/** 
 * @ClassName: Favorite 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月27日 上午8:56:26  
 */
public class Favorite implements Serializable{
	private int id;
	private String text;
	private String url;
	private int userId;
	private String created;
	/** 
	 * @Title:Favorite
	 * @Description:TODO  
	 */
	public Favorite() {
		super();
		// TODO Auto-generated constructor stub
	}
	/** 
	 * @Title:Favorite
	 * @Description:TODO 
	 * @param id
	 * @param text
	 * @param url
	 * @param userId
	 * @param created 
	 */
	public Favorite(int id, String text, String url, int userId, String created) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.userId = userId;
		this.created = created;
	}
	/* (non Javadoc) 
	 * @Title: toString
	 * @Description: TODO
	 * @return 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "Favorite [id=" + id + ", text=" + text + ", url=" + url + ", userId=" + userId + ", created=" + created
				+ "]";
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}
	
	
}

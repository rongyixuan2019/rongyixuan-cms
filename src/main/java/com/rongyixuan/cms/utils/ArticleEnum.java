/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleEnum.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.utils 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月22日 下午7:18:11 
 * @version: V1.0   
 */
package com.rongyixuan.cms.utils;

/** 
 * @ClassName: ArticleEnum 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月22日 下午7:18:11  
 */
public enum ArticleEnum {
	HTML(0,"html"),IMAGE(1,"image");
	
	private Integer code;
	private String name;
	/** 
	 * @Title:ArticleEnum
	 * @Description:TODO 
	 * @param code
	 * @param name 
	 */
	private ArticleEnum(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		System.out.println(HTML.getCode());
		System.out.println(HTML.getName());
		//IMAGE.getCode();
	}
}

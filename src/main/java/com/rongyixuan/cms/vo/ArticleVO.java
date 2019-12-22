/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleVO.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.vo 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月22日 下午7:07:01 
 * @version: V1.0   
 */
package com.rongyixuan.cms.vo;

import org.springframework.data.elasticsearch.annotations.Document;

import com.rongyixuan.cms.domain.ArticleWithBLOBs;

/** 
 * @ClassName: ArticleVO 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月22日 下午7:07:01  
 */

public class ArticleVO extends ArticleWithBLOBs {
	private String url;//图片集的单个图片地址
	private String descr;//图片描述
	/** 
	 * @Title:ArticleVO
	 * @Description:TODO  
	 */
	public ArticleVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/** 
	 * @Title:ArticleVO
	 * @Description:TODO 
	 * @param url
	 * @param descr 
	 */
	public ArticleVO(String url, String descr) {
		super();
		this.url = url;
		this.descr = descr;
	}
	/* (non Javadoc) 
	 * @Title: toString
	 * @Description: TODO
	 * @return 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "ArticleVO [url=" + url + ", descr=" + descr + ", toString()=" + super.toString() + "]";
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
	 * @return the descr
	 */
	public String getDescr() {
		return descr;
	}
	/**
	 * @param descr the descr to set
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	
}

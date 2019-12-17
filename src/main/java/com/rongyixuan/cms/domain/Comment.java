/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: Comment.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月22日 下午3:29:27 
 * @version: V1.0   
 */
package com.rongyixuan.cms.domain;

import java.io.Serializable;
import java.util.Date;

/** 
 * @ClassName: Comment 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月22日 下午3:29:27  
 */
public class Comment implements Serializable{
	
	private Integer id;
	
	/**所属文章**/
	private Article article;
	
	/**作者**/
	private User author;
	
	/**评论内容**/
	private String content;
	
	/**评论时间**/
	private Date created;

	/** 
	 * @Title:Comment
	 * @Description:TODO  
	 */
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/** 
	 * @Title:Comment
	 * @Description:TODO 
	 * @param id
	 * @param article
	 * @param author
	 * @param content
	 * @param created 
	 */
	public Comment(Integer id, Article article, User author, String content, Date created) {
		super();
		this.id = id;
		this.article = article;
		this.author = author;
		this.content = content;
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
		return "Comment [id=" + id + ", article=" + article + ", author=" + author + ", content=" + content
				+ ", created=" + created + "]";
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the article
	 */
	public Article getArticle() {
		return article;
	}

	/**
	 * @param article the article to set
	 */
	public void setArticle(Article article) {
		this.article = article;
	}

	/**
	 * @return the author
	 */
	public User getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(User author) {
		this.author = author;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	
}
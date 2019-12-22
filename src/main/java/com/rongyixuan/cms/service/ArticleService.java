/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleService.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.service 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月20日 下午2:12:21 
 * @version: V1.0   
 */
package com.rongyixuan.cms.service;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.rongyixuan.cms.domain.Article;
import com.rongyixuan.cms.domain.ArticleWithBLOBs;

/** 
 * @ClassName: ArticleService 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月20日 下午2:12:21  
 */

public interface ArticleService {
	
	/**
	 * 最新文章用单独的方法进行查询（主页面）
	 * 最新文章
	 * @param article
	 * @param page
	 * @param pageSize
	 * @return
	 */
	PageInfo<Article> selectLast(Article article,Integer page,Integer pageSize);
	/**
	 * 热门文章用单独的方法进行查询（主页面显示的）
	 * @param article
	 * @param page
	 * @param pageSize
	 * @return
	 */
	PageInfo<Article> selectHot(Article article,Integer page,Integer pageSize);


	/** 
	 * @Title: selectByPrimaryKey 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: ArticleWithBLOBs
	 */
	ArticleWithBLOBs selectByPrimaryKey(Integer id);

	/** 
	 * @Title: selects 
	 * @Description: TODO
	 * @param article
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> selects(Article article, Integer page, Integer pageSize);

	/** 
	 * @Title: update 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: boolean
	 */
	boolean update(ArticleWithBLOBs article);

	/** 
	 * @Title: insertSelective 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: boolean
	 */
	boolean insertSelective(ArticleWithBLOBs article);
	/** 
	 * 高亮查询
	 * @Title: selectES 
	 * @Description: TODO
	 * @param page
	 * @param pageSize
	 * @param key
	 * @return
	 * @return: PageInfo<Article>
	 */
	PageInfo<Article> selectES(Integer page, Integer pageSize, String key);
	/** 
	 * @Title: updateByPrimaryKeySelective 
	 * @Description: TODO
	 * @param article
	 * @return: void
	 */
	void updateByPrimaryKeySelective(ArticleWithBLOBs article);

}

package com.rongyixuan.cms.dao;

import java.util.List;

import com.rongyixuan.cms.domain.Article;
import com.rongyixuan.cms.domain.ArticleWithBLOBs;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);

	/** 管理员查询文章列表
	 * @Title: selects 
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: List<Article>
	 */
	List<Article> selects(Article article);
}
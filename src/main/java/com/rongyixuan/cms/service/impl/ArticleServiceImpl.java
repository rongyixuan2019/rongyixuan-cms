/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleServiceImpl.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.service.impl 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月20日 下午2:36:47 
 * @version: V1.0   
 */
package com.rongyixuan.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rongyixuan.cms.dao.ArticleMapper;
import com.rongyixuan.cms.domain.Article;
import com.rongyixuan.cms.domain.ArticleWithBLOBs;
import com.rongyixuan.cms.service.ArticleService;

/** 
 * @ClassName: ArticleServiceImpl 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月20日 下午2:36:47  
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Resource
	private RedisTemplate< String , Article> redisTemplate;
	
	@Resource
	private ArticleMapper articleMapper;
	
	/* 管理员查询文章详情(non Javadoc) 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.rongyixuan.cms.service.ArticleService#selectByPrimaryKey(java.lang.Integer) 
	 */
	@Override
	public ArticleWithBLOBs selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMapper.selectByPrimaryKey(id);
	}

	/* 管理员查询文章列表(non Javadoc) 
	 * @Title: selects
	 * @Description: TODO
	 * @param article
	 * @param page
	 * @param pageSize
	 * @return 
	 * @see com.rongyixuan.cms.service.ArticleService#selects(com.rongyixuan.cms.domain.Article, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public PageInfo<Article> selects(Article article, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Article> articles = articleMapper.selects(article);
		PageInfo<Article> info = new PageInfo<Article>(articles);
		return info;
	}

	/* 
	 * 管理员修改文章
	 * (non Javadoc) 
	 * @Title: update
	 * @Description: TODO
	 * @param article
	 * @return 
	 * @see com.rongyixuan.cms.service.ArticleService#update(com.rongyixuan.cms.domain.ArticleWithBLOBs) 
	 */
	@Override
	public boolean update(ArticleWithBLOBs article) {
		try {
			int result = articleMapper.updateByPrimaryKeySelective(article);
			//审核通过文章
			if(result > 0 && article.getStatus() != null && article.getStatus() == 1) {
				//判断当前要审核文章
				//审核文章通过以后，要清空redis中对应的数据
				
				//清空redis
				redisTemplate.delete("last_article");
			}
			
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("修改失败");
		}
	}

	/*用户添加、发布文章(non Javadoc) 
	 * @Title: insertSelective
	 * @Description: TODO
	 * @param article
	 * @return 
	 * @see com.rongyixuan.cms.service.ArticleService#insertSelective(com.rongyixuan.cms.domain.ArticleWithBLOBs) 
	 */
	@Override
	public boolean insertSelective(ArticleWithBLOBs record) {
		try {
			
			return articleMapper.insertSelective(record)>0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("发布失败");
		}
	}

	/* (non Javadoc) 
	 * 最新文章用单独的方法进行查询（主页面）
	 * @Title: selectLast
	 * @Description: TODO
	 * @param article
	 * @param page
	 * @param pageSize
	 * @return 
	 * @see com.rongyixuan.cms.service.ArticleService#selectLast(com.rongyixuan.cms.domain.Article, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public PageInfo<Article> selectLast(Article article, Integer page, Integer pageSize) {
		//第一次访问的时候，redis中没有数据，从mysql数据库中获取数据
		//怎么判断是第一次访问？
		//直接查看redis中有没有对应的数据，如果没有，则为第一次访问
		//之后再次访问时，直接从redis中获取数据
		//获取List类型的操作对象
		ListOperations<String, Article> opsForList = redisTemplate.opsForList();
		List<Article> articles = null;
		
		if(redisTemplate.hasKey("last_article")) {
			//如果有对应的键，则直接从redis中获取数据
			//获取数据
			articles = opsForList.range("last_article", 0, -1);
		}else {
			//如果没有对应的键
			//从mysql中获取数据
			PageHelper.startPage(page, pageSize);
			articles = articleMapper.selects(article);
			//获取完数据以后，存入redis中
			opsForList.rightPushAll("last_article", articles);
		}
		return new PageInfo<Article>(articles);
	}

	/* (non Javadoc) 
	 * 热门文章用单独的方法进行查询（主页面显示的）
	 * @Title: selectHot
	 * @Description: TODO
	 * @param article
	 * @param page
	 * @param pageSize
	 * @return 
	 * @see com.rongyixuan.cms.service.ArticleService#selectHot(com.rongyixuan.cms.domain.Article, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public PageInfo<Article> selectHot(Article article, Integer page, Integer pageSize) {
		// 第一次访问的时候，redis中没有数据，从mysql数据库中获取数据
		// 怎么判断是第一次访问？
		// 直接查看redis中有没有对应的数据，如果没有，则为第一次访问
		// 之后再次访问时，直接从redis中获取数据

		// 获取List类型的操作对象
		ListOperations<String, Article> opsForList = redisTemplate.opsForList();

		PageInfo<Article> pageInfo = null;

		if (redisTemplate.hasKey("hot_article")) {
			// 如果有对应的键，则直接从redis中获取数据

			// 获取数据
			List<Article> articles = opsForList.range("hot_article", (page - 1) * pageSize, page * pageSize - 1);

			// 设置数据
			pageInfo = new PageInfo<Article>(articles, 3);
			// 上一页
			pageInfo.setPrePage(page > 1 ? page - 1 : 1);
			// 当前页
			pageInfo.setPageNum(page);

			// 获取总条数
			Long size = opsForList.size("hot_article");
			// 获取总页数
			int pages = (int) ((size + pageSize - 1) / pageSize);

			// 下一页
			pageInfo.setNextPage(page >= pages ? pages : (page + 1));

		} else {
			// 如果没有对应的键
			// 从mysql中获取所有热门文章的数据
			List<Article> all_articles = articleMapper.selects(article);

			// 获取全部数据以后，存入redis中
			opsForList.rightPushAll("hot_article", all_articles);

			// 获取分页数据，显示出来
			PageHelper.startPage(page, pageSize);
			// 设置数据
			pageInfo = new PageInfo<Article>(articleMapper.selects(article));

		}

		return pageInfo;
	}

}

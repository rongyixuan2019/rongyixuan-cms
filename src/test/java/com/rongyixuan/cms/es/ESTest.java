package com.rongyixuan.cms.es;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rongyixuan.cms.dao.ArticleMapper;
import com.rongyixuan.cms.domain.Article;
import com.rongyixuan.cms.domain.Category;
import com.rongyixuan.cms.domain.Channel;
import com.rongyixuan.cms.domain.User;
import com.rongyixuan.cms.utils.ESUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ESTest {

	@Resource
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Resource
	private ArticleMapper articleMapper;
	
	//新增数据
	@Test
	public void testAdd() {
		IndexQuery query = new IndexQuery();
		
		for(int i=21;i<=30;i++) {
			//创建实体类对象
			Article article = new Article();
			article.setId(i);
			article.setTitle("article"+i);
			article.setCreated(new Date());
			
			//设置查询条件
			query.setId(article.getId().toString());
			query.setObject(article);
			
			elasticsearchTemplate.index(query );
		}
	}
	
	//修改数据
	@Test
	public void testUpdate() {
		IndexQuery query = new IndexQuery();
		
		//创建实体类对象
		Article article = new Article();
		article.setId(1);
		article.setTitle("修改文章1");
		article.setCreated(new Date());
		
		//设置查询条件
		query.setId(article.getId().toString());
		query.setObject(article);
		
		elasticsearchTemplate.index(query );
	}
	
	//删除数据
	@Test
	public void testDel() {
		//根据唯一标识删除数据
		elasticsearchTemplate.delete(Article.class, "1");
		
	}
	
	//查询单个数据
	@Test
	public void testFindOne() {
		//封装查询对象
		GetQuery query = new GetQuery();
		
		//设置唯一标识
		query.setId("1");
		
		//查询数据
		Article article = elasticsearchTemplate.queryForObject(query , Article.class);
		
		System.out.println(article);
	}
	
	//查询全部数据
	@Test
	public void testFindAll() {
		//封装查询条件
		SearchQuery query = new NativeSearchQueryBuilder().build();
		
		List<Article> list = elasticsearchTemplate.queryForList(query , Article.class);
		
		for (Article article : list) {
			System.out.println(article);
		}
	}
	
	
	//模糊查询数据
	@Test
	public void testFindMH() {
		//封装模糊条件
		QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("测试", "title");
		
		//封装查询条件
		SearchQuery query = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
		
		List<Article> list = elasticsearchTemplate.queryForList(query , Article.class);
		
		for (Article article : list) {
			System.out.println(article);
		}
	}
	
	
	//分页查询数据
	@Test
	public void testFindPage() {
		//封装分页数据
		Pageable pageable = PageRequest.of(0, 5, Sort.by(Direction.ASC, "id"));
		//封装查询条件
		SearchQuery query = new NativeSearchQueryBuilder().withPageable(pageable).build();
		
		AggregatedPage<Article> queryForPage = elasticsearchTemplate.queryForPage(query , Article.class);
		 
		
		List<Article> list = queryForPage.getContent();
		
		for (Article article : list) {
			System.out.println(article);
		}
	}
	//分页+ 模糊查询数据
	@Test
	public void testFindPageAndMH() {
		//封装模糊条件
		QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("修改", "title");
		
		//封装分页数据
		Pageable pageable = PageRequest.of(0, 5, Sort.by(Direction.ASC, "id"));
		
		//封装查询条件
		SearchQuery query = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(queryBuilder).build();
		
		AggregatedPage<Article> queryForPage = elasticsearchTemplate.queryForPage(query , Article.class);
		
		List<Article> list = queryForPage.getContent();
		
		for (Article article : list) {
			System.out.println(article);
		}
	}
	
	//高亮查询
	@Test
	public void testHighLight() {
		
		//实体类中成员变量如果是实体类类型，则将其类对象，存入clazzs中
		Class [] clazzs = new Class[] {User.class,Category.class,Channel.class};
		
		AggregatedPage<Article> selectObjects = ESUtils.selectObjects(elasticsearchTemplate, Article.class, Arrays.asList(clazzs), 0, 30, "id", new String[] {"title"}, "司机");
		
		List<Article> content = selectObjects.getContent();
		
		for (Object object : content) {
			System.out.println(object);
		}
	}
	
	
	/**
	 * 从mysql中获取数据，存入es中
	 */
	@Test
	public void testToES(){
		//查询所有article数据
		List<Article> list = articleMapper.selects(new Article());
		
		//将数据存入es中
		for (Article article : list) {
			
			IndexQuery query = new IndexQuery();
			
			query.setObject(article);
			
			elasticsearchTemplate.index(query);
		}
		
		System.out.println("存储完毕");
	}
}

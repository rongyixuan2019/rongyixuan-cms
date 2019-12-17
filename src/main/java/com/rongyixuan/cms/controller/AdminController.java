/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: AdminController.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.controller 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月20日 下午1:43:40 
 * @version: V1.0   
 */
package com.rongyixuan.cms.controller;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.rongyixuan.cms.domain.Article;
import com.rongyixuan.cms.domain.ArticleWithBLOBs;
import com.rongyixuan.cms.domain.Links;
import com.rongyixuan.cms.domain.User;
import com.rongyixuan.cms.service.ArticleService;
import com.rongyixuan.cms.service.LinksService;
import com.rongyixuan.cms.service.UserService;
import com.rongyixuan.cms.utils.Result;
import com.rongyixuan.cms.utils.ResultUtil;
import com.rongyixuan.cms.vo.ArticleVO;

/** 
 * @ClassName: AdminController 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月20日 下午1:43:40  
 */
@RequestMapping("admin")
@Controller
public class AdminController {

	@Resource
	private ArticleService articleService;

	@Resource
	private UserService userService;
	
	@Resource
	private LinksService linksService;
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 维护友情链接--列表
	 * @return
	 * @return: String
	 */
	@GetMapping("links/selects")
	public String selects(Model model,@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize) {
		PageInfo<Links> info = linksService.selects(page, pageSize);	
		model.addAttribute("info", info);
		return "admin/links/links";
	}
	
	/**
	 * 
	 * @Title: add 
	 * @Description: 跳转到增加友情链接页面
	 * @return
	 * @return: String
	 */
	@GetMapping("links/add")
	public String add() {
		return "admin/links/add";
		
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@PostMapping("links/add")
	public Result<Links> add(Links links){
		linksService.insert(links);
		return ResultUtil.success();
	}
	
	/**
	 * 管理员进入管理员后台的首页
	 * @Title: index 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"","/","index"})
	public String index() {
		return "admin/index";
	}
	
	/**
	 * 管理员进行用户的列表查询
	 * @Title: users 
	 * @Description: TODO
	 * @param user
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping("user/users")
	public ModelAndView users(User user,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize) {
		ModelAndView mv = new ModelAndView("/admin/user/users");
		PageInfo<User> info = userService.selects(user, page, pageSize);
		mv.addObject("users", info.getList());
		mv.addObject("user", user);	
		mv.addObject("info", info);
		int[] nums = info.getNavigatepageNums();
		mv.addObject("nums", nums);
		return mv;
	}
	
	/**
	 * 管理员修改个人用户
	 * @Title: update
	 * @Description: 修改改用
	 * @return
	 * @return: boolean
	 */
	@RequestMapping("user/update")
	@ResponseBody
	public boolean update(User user) {
		return userService.update(user);

	}
	
	/**
	 * 管理员进行查询文章详情
	 * 
	 * @Title: detail
	 * @Description: 
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("article/article")
	public String detail(Model model, Integer id) {
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		if(article.getContentType()==0) {
			return "admin/article/article";
		}else {
			String string = article.getContent();
			ArrayList<ArticleVO> list = new ArrayList<ArticleVO>();
			Gson gson = new Gson();
			JsonArray array = new JsonParser().parse(string).getAsJsonArray();
			for (JsonElement jsonElement : array) {
				//把json转为java
				ArticleVO vo = gson.fromJson(jsonElement, ArticleVO.class);
				list.add(vo);
			}
			model.addAttribute("title", article.getTitle());// 标题
			model.addAttribute("list", list);// 标题包含的 图片的地址和描述
			return "admin/article/articlepic";
		}
	}
	
	/**
	 *管理元进行 文章列表查询
	 * 
	 * @Title: articles
	 * @Description: TODO
	 * @param model
	 * @param article
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@RequestMapping("article/articles")
	public String articles(Model model, Article article, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize) {
		PageInfo<Article> info = articleService.selects(article, page, pageSize);
		model.addAttribute("info", info);// 封装的查询结国
		model.addAttribute("article", article);// 封装的查询
		return "admin/article/articles";
	}
	
	
	/**
	 * 管理员修改文章
	 * 
	 * @Title: update
	 * @Description: TODO
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@RequestMapping("article/update")
	public boolean update(ArticleWithBLOBs article) {
		return articleService.update(article);
	}
	
}

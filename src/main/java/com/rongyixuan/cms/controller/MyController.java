/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: MyController.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.controller 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月21日 上午11:22:23 
 * @version: V1.0   
 */
package com.rongyixuan.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.rongyixuan.cms.domain.Article;
import com.rongyixuan.cms.domain.ArticleWithBLOBs;
import com.rongyixuan.cms.domain.Collect;
import com.rongyixuan.cms.domain.User;
import com.rongyixuan.cms.service.ArticleService;
import com.rongyixuan.cms.service.CollectService;
import com.rongyixuan.cms.utils.ArticleEnum;
import com.rongyixuan.cms.utils.Result;
import com.rongyixuan.cms.utils.ResultUtil;
import com.rongyixuan.cms.vo.ArticleVO;

/** 
 * @ClassName: MyController 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月21日 上午11:22:23  
 */
@RequestMapping("my")
@Controller
public class MyController {
	@Resource
	private ArticleService articleService;
	
	@Resource
	private CollectService collectService;// 我的收藏
	
	/**
	 * 
	 * @Title: collects
	 * @Description: 用户查看我的收藏
	 * @return
	 * @return: String
	 */
	@GetMapping("collects")
	public String collects(HttpServletRequest request, Model model, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "3") Integer pageSize) {
		HttpSession session = request.getSession(false);

		User user = (User) session.getAttribute("user");

		PageInfo<Collect> info = collectService.selects(page, pageSize, user);

		model.addAttribute("info", info);
		return "my/collect/collects";

	}
	
	/**
	 * 
	 * @Title: deleteCollect 
	 * @Description: 用户自己移除收藏
	 * @param id
	 * @return
	 * @return: Result<Collect>
	 */
	@ResponseBody
	@PostMapping("deleteCollect")
	public Result<Collect> deleteCollect(Integer id){
		collectService.deleteById(id);
		return ResultUtil.success();
	}
	
	
	/**
	 * 去个人中心首页面
	 * @Title: index 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = {"","/","index"})
	public String index() {
		return "my/index";
	}
	
	
	/**
	 * 用户通过个人中心文章列表查询自己的文章详情
	 * @Title: detail 
	 * @Description: TODO
	 * @param model
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("article")
	public String detail(Model model,Integer id) {
		ArticleWithBLOBs article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		if(article.getContentType()==0) {
			return "my/article/article";
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
			return "/my/article/articlepic";
		}
		
		
	}
	
	/**
	 * 个人主页中返回我自己发表的文章
	 * @Title: selectByUser 
	 * @Description: TODO
	 * @param model
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: String
	 */
	@GetMapping("selectByUser")
	public String selectByUser(HttpServletRequest request,Model model,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3") Integer pageSize) {
		Article a = new Article();
		HttpSession session = request.getSession(false);
		  if(session==null) {
			  return "redirect:/passport/login";//session.可能过期
		  }
		  
		  User user = (User) session.getAttribute("user");
		if(user!=null) {
		a.setUserId(user.getId());
		  }
		PageInfo<Article> info = articleService.selects(a, page, pageSize);
		model.addAttribute("info", info);
		return "my/article/articles";
	}
	
	/**
	 * 
	 * @Title: publish 
	 * @Description: 去 增加文章/发布文章页面
	 * @return
	 * @return: String
	 */
	@GetMapping("publish")
	public String publish() {
		return "my/article/publish";
	}
	
	/**
	 * 
	 * @Title: publish 
	 * @Description: 增加文章/发布文章
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("publish")
	public boolean  publish(HttpServletRequest request, ArticleWithBLOBs article,MultipartFile file) {
		
		  if(!file.isEmpty()) {
			 //文件上传路径.把文件放入项目的 /resource/pic  下
			  String path = request.getSession().getServletContext().getRealPath("/resource/pic/");
			  //为了防止文件重名.使用UUID 的方式重命名上传的文件
			  String oldFilename = file.getOriginalFilename();
			  //a.jpg
			  String newFilename =UUID.randomUUID()+oldFilename.substring(oldFilename.lastIndexOf("."));
			  File f = new File(path,newFilename);
			  //写入硬盘
			  try {
				file.transferTo(f);
				
				article.setPicture(newFilename);//标题图片
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			  
		  }
		  //初始化设置
		  article.setStatus(0);//待审核
		  HttpSession session = request.getSession(false);
		  if(session==null) {
			  return false;
		  }
		  User user = (User) session.getAttribute("user");
		  if(user!=null) {
			  article.setUserId(user.getId());//发布人
		  }else {
			  article.setUserId(137);
		  }
		  article.setHits(0);
		  article.setHot(0);
		  article.setDeleted(0);
		  article.setCreated(new Date());
		  article.setUpdated(new Date());
		  article.setContentType(ArticleEnum.HTML.getCode());
		return articleService.insertSelective(article);
		
	}
	
	/**
	 * 
	 * @Title: publish
	 * @Description: 去 发布图片集
	 * @return
	 * @return: String
	 */
	@GetMapping("publishpic")
	public String publishpic() {

		return "my/article/publishpic";

	}
	
	
	/**
	 * 
	 * @Title: publish
	 * @Description: 增加图片集/发布图片集
	 * @param article
	 * @return
	 * @return: boolean
	 */
	@ResponseBody
	@PostMapping("publishpic")
	public boolean publishpic(HttpServletRequest request,ArticleWithBLOBs article, MultipartFile[] files, String[] descr) {
		
		String newFilename=null;
		List<ArticleVO> list =new ArrayList<ArticleVO>();//用来存放图片的地址和描述
		int i =0;
		for (MultipartFile file : files) {
			ArticleVO vo = new ArticleVO();
			if (!file.isEmpty()) {
				// 文件上传路径.把文件放入项目的 /resource/pic 下
				String path = request.getSession().getServletContext().getRealPath("/resource/pic/");
				// 为了防止文件重名.使用UUID 的方式重命名上传的文件
				String oldFilename = file.getOriginalFilename();
				// a.jpg
				 newFilename = UUID.randomUUID() + oldFilename.substring(oldFilename.lastIndexOf("."));
				File f = new File(path, newFilename);
				vo.setUrl(newFilename);
				vo.setDescr(descr[i]);
				i++;
				list.add(vo);
				
				// 写入硬盘
				try {
					file.transferTo(f);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}	
		}
		article.setPicture(newFilename);// 标题图片
		Gson gson= new Gson();
		//使用gson,把java对象转为json
		article.setContent(gson.toJson(list));
		// 初始化设置
		article.setStatus(0);// 待审核
		HttpSession session = request.getSession(false);
		if (session == null) {
			return false;
		}
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());// 发布人
		article.setHits(0);
		article.setHot(0);
		article.setDeleted(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		//图片集标识
		article.setContentType(ArticleEnum.IMAGE.getCode());
		
		return articleService.insertSelective(article);
	   
	}


}

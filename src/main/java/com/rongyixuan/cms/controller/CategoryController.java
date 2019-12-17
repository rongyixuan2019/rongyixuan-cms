/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CategoryController.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.controller 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月21日 下午1:41:57 
 * @version: V1.0   
 */
package com.rongyixuan.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongyixuan.cms.domain.Category;
import com.rongyixuan.cms.service.CategoryService;

/** 
 * @ClassName: CategoryController 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月21日 下午1:41:57  
 */
@RequestMapping("category")
@Controller
public class CategoryController {
	@Resource
	private CategoryService categoryService;
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 根据栏目查询分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	@ResponseBody
	@RequestMapping("selects")
	private List<Category> selects(Integer channelId){
		return categoryService.selectsByChannelId(channelId);
	}
}

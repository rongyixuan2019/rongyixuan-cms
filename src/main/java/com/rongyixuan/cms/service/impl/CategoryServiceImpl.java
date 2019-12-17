/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CategoryServiceImpl.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.service.impl 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月21日 下午1:44:33 
 * @version: V1.0   
 */
package com.rongyixuan.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongyixuan.cms.dao.CategoryMapper;
import com.rongyixuan.cms.domain.Category;
import com.rongyixuan.cms.service.CategoryService;

/** 
 * @ClassName: CategoryServiceImpl 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月21日 下午1:44:33  
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Resource
	private CategoryMapper categoryMapper;
	/* (non Javadoc) 
	 * @Title: selectsByChannelId
	 * @Description: TODO
	 * @param channelId
	 * @return 
	 * @see com.rongyixuan.cms.service.CategoryService#selectsByChannelId(java.lang.Integer) 
	 */
	@Override
	public List<Category> selectsByChannelId(Integer channelId) {
		return categoryMapper.selectsByChannelId(channelId);
	}

}

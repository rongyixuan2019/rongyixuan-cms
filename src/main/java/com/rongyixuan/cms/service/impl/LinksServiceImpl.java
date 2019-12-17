/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: LinksService.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.service.impl 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月24日 下午2:04:38 
 * @version: V1.0   
 */
package com.rongyixuan.cms.service.impl;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rongyixuan.cms.dao.LinksMapper;
import com.rongyixuan.cms.domain.Links;
import com.rongyixuan.cms.service.LinksService;
import com.rongyixuan.cms.utils.CMSAjaxException;
import com.xuanxuan.common.StringUtil;

/** 
 * @ClassName: LinksService 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月24日 下午2:04:38  
 */
@Service
public class LinksServiceImpl implements LinksService {
	
	@Resource
	private LinksMapper linksMapper;
	/* 管理员添加链接(non Javadoc) 
	 * @Title: insert
	 * @Description: TODO
	 * @param links
	 * @return 
	 * @see com.rongyixuan.cms.service.LinksService#insert(com.rongyixuan.cms.domain.Links) 
	 */
	@Override
	public boolean insert(Links links) {
		  // 调用工具类判断是否是有效URL
		if (!StringUtil.isHttpUrl(links.getUrl())) throw new
		  CMSAjaxException(1, "不是有效的url"); 
		
		  links.setCreated(new Date());
		  linksMapper.insert(links);
		  return true;
	}

	/* 管理员查看全部链接(non Javadoc) 
	 * @Title: selects
	 * @Description: TODO
	 * @param page
	 * @param pageSize
	 * @return 
	 * @see com.rongyixuan.cms.service.LinksService#selects(java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public PageInfo<Links> selects(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Links> list = linksMapper.selects();
		return new PageInfo<Links>(list);
	}

}

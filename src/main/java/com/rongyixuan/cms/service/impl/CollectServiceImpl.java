/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CollectServiceImpl.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.service.impl 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月24日 下午7:31:16 
 * @version: V1.0   
 */
package com.rongyixuan.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rongyixuan.cms.dao.CollectMapper;
import com.rongyixuan.cms.domain.Collect;
import com.rongyixuan.cms.domain.User;
import com.rongyixuan.cms.service.CollectService;
import com.rongyixuan.cms.utils.CMSAjaxException;
import com.xuanxuan.common.StringUtil;

/** 
 * @ClassName: CollectServiceImpl 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月24日 下午7:31:16  
 */
@Service
public class CollectServiceImpl implements CollectService {
	
	@Resource
	private CollectMapper collectMapper;
	/* (non Javadoc) 
	 * @Title: deleteById
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.rongyixuan.cms.service.CollectService#deleteById(java.lang.Integer) 
	 */
	@Override
	public boolean insert(Collect collect) {
		if(!StringUtil.isHttpUrl(collect.getUrl()))
			throw new CMSAjaxException(1, "不是有效的URL");
		collect.setCreated(new Date());
		collectMapper.insert(collect);
		return true;
	}

	@Override
	public PageInfo<Collect> selects(Integer page,Integer pageSize ,User user) {
		PageHelper.startPage(page, pageSize);
		List<Collect> list = collectMapper.selects( user);
		return new PageInfo<Collect>(list);
	}

	@Override
	public boolean deleteById(Integer id) {
		collectMapper.deleteById(id);
		return true;
	}

	@Override
	public int selectByText(String text, User user) {
		return collectMapper.selectByText(text, user);
	}
}

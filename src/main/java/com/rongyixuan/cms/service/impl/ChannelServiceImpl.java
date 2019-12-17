/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ChannelServiceImpl.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.service.impl 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月21日 下午2:04:50 
 * @version: V1.0   
 */
package com.rongyixuan.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rongyixuan.cms.dao.ChannelMapper;
import com.rongyixuan.cms.domain.Channel;
import com.rongyixuan.cms.service.ChannelService;

/** 
 * @ClassName: ChannelServiceImpl 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月21日 下午2:04:50  
 */
@Service
public class ChannelServiceImpl implements ChannelService{
	
	@Resource
	private ChannelMapper channelMapper;
	/* 查询全部栏目(non Javadoc) 
	 * @Title: selects
	 * @Description: TODO
	 * @return 
	 * @see com.rongyixuan.cms.service.ChannelService#selects() 
	 */
	@Override
	public List<Channel> selects() {
		return channelMapper.selects();
	}

}

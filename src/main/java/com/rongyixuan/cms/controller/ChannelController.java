/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ChannelController.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.controller 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月21日 下午2:03:20 
 * @version: V1.0   
 */
package com.rongyixuan.cms.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongyixuan.cms.domain.Channel;
import com.rongyixuan.cms.service.ChannelService;

/** 
 * @ClassName: ChannelController 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月21日 下午2:03:20  
 */
@RequestMapping("channel")
@Controller
public class ChannelController {
	@Resource
	private ChannelService channelService;
	
	/**
	 * 
	 * @Title: channels 
	 * @Description: 所有栏目
	 * @return
	 * @return: List<Channel>
	 */
	@ResponseBody
	@RequestMapping("channels")
	public List <Channel> channels(){
		return channelService.selects();
	}
}

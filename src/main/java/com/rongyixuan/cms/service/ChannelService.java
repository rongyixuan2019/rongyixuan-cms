/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ChannelService.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.service 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月21日 下午2:04:32 
 * @version: V1.0   
 */
package com.rongyixuan.cms.service;

import java.util.List;

import com.rongyixuan.cms.domain.Channel;

/** 
 * @ClassName: ChannelService 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月21日 下午2:04:32  
 */
public interface ChannelService {

	/** 
	 * @Title: selects 
	 * @Description: TODO
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();

}

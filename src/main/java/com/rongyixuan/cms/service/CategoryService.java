/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CategoryService.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.service 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月21日 下午1:43:19 
 * @version: V1.0   
 */
package com.rongyixuan.cms.service;

import java.util.List;

import com.rongyixuan.cms.domain.Category;

/** 
 * @ClassName: CategoryService 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月21日 下午1:43:19  
 */
public interface CategoryService {

	/** 
	 * @Title: selectsByChannelId 
	 * @Description: TODO
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByChannelId(Integer channelId);

}

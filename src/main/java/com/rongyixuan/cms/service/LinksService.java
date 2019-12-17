/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: LinksService.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.service 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月24日 下午2:01:09 
 * @version: V1.0   
 */
package com.rongyixuan.cms.service;

import com.github.pagehelper.PageInfo;
import com.rongyixuan.cms.domain.Links;

/** 
 * @ClassName: LinksService 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月24日 下午2:01:09  
 */
public interface LinksService {
	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加
	 * @param links
	 * @return
	 * @return: int
	 */
	boolean insert(Links links);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 列表
	 * @return
	 * @return: List<Links>
	 */
   PageInfo<Links> selects(Integer page,Integer pageSize);
}

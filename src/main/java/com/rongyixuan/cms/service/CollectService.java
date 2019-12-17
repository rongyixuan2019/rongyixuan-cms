/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CollectService.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.service 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月24日 下午7:30:14 
 * @version: V1.0   
 */
package com.rongyixuan.cms.service;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.rongyixuan.cms.domain.Collect;
import com.rongyixuan.cms.domain.User;

/** 
 * @ClassName: CollectService 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月24日 下午7:30:14  
 */
public interface CollectService {

	/** 删除收藏
	 * @Title: deleteById 
	 * @Description: TODO
	 * @param id
	 * @return: void
	 */
	boolean  deleteById(Integer id);

	/** 收藏列表
	 * @Title: selects 
	 * @Description: TODO
	 * @param page
	 * @param pageSize
	 * @param user
	 * @return
	 * @return: PageInfo<Collect>
	 */
	PageInfo<Collect> selects(Integer page, Integer pageSize, User user);
	
	/**
	    * 
	    * @Title: selectByText 
	    * @Description: 根据登录人和文章标题查询是否收藏
	    * @param text
	    * @return
	    * @return: int
	    */
	   int selectByText(@Param("text")String text ,@Param("user")User user);

	   /**
		 * 
		 * @Title: insert 
		 * @Description: 增加
		 * @param links
		 * @return
		 * @return: int
		 */
		boolean insert(Collect Collect);
}

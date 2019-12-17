/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CollectMapper.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.dao 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月24日 下午7:39:20 
 * @version: V1.0   
 */
package com.rongyixuan.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rongyixuan.cms.domain.Collect;
import com.rongyixuan.cms.domain.User;

/** 
 * @ClassName: CollectMapper 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月24日 下午7:39:20  
 */
public interface CollectMapper {

	/** 
	 * @Title: insert 
	 * @Description: TODO
	 * @param collect
	 * @return: void
	 */
	int insert(Collect collect);

	/** 
	 * @Title: selects 
	 * @Description: TODO
	 * @param user
	 * @return
	 * @return: List<Collect>
	 */
	List<Collect> selects(User user);

	/** 
	 * @Title: deleteById 
	 * @Description: TODO
	 * @param id
	 * @return: void
	 */
	int deleteById(Integer id);

	/** 
	 * @Title: selectByText 
	 * @Description: TODO
	 * @param text
	 * @param user
	 * @return
	 * @return: int
	 */
	int selectByText(@Param("text")String text,@Param("user") User user);

}

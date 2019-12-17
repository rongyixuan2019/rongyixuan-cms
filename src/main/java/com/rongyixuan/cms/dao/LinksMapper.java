/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: LinksMapper.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.dao 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月24日 下午2:13:33 
 * @version: V1.0   
 */
package com.rongyixuan.cms.dao;

import java.util.List;

import com.rongyixuan.cms.domain.Links;

/** 
 * @ClassName: LinksMapper 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月24日 下午2:13:33  
 */
public interface LinksMapper {

	/** 
	 * @Title: insert 
	 * @Description: TODO
	 * @param links
	 * @return: void
	 */
	void insert(Links links);

	/** 
	 * @Title: selects 
	 * @Description: TODO
	 * @return
	 * @return: List<Links>
	 */
	List<Links> selects();

}

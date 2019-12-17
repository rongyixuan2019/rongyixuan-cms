/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserService.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.service 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月20日 下午2:11:50 
 * @version: V1.0   
 */
package com.rongyixuan.cms.service;

import com.github.pagehelper.PageInfo;
import com.rongyixuan.cms.domain.User;
import com.rongyixuan.cms.vo.UserVO;

/** 
 * @ClassName: UserService 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月20日 下午2:11:50  
 */
public interface UserService {

	/** 
	 * @Title: selects 
	 * @Description: TODO
	 * @param user
	 * @param page
	 * @param pageSize
	 * @return
	 * @return: PageInfo<User>
	 */
	PageInfo<User> selects(User user, Integer page, Integer pageSize);

	/** 
	 * @Title: update 
	 * @Description: TODO
	 * @param user
	 * @return
	 * @return: boolean
	 */
	boolean update(User user);

	/** 
	 * @Title: login 
	 * @Description: TODO
	 * @param user
	 * @return
	 * @return: User
	 */
	User login(User user);

	/** 
	 * @Title: insertSelective 
	 * @Description: TODO
	 * @param userVO
	 * @return
	 * @return: boolean
	 */
	boolean insertSelective(UserVO userVO);

}

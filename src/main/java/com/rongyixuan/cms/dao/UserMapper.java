package com.rongyixuan.cms.dao;

import java.util.List;

import com.rongyixuan.cms.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	/** 用户列表查询
	 * @Title: selects 
	 * @Description: TODO
	 * @param user
	 * @return
	 * @return: List<User>
	 */
	List<User> selects(User user);

	/** 
	 * @Title: selectByName 
	 * @Description: TODO
	 * @param username
	 * @return
	 * @return: User
	 */
	User selectByName(String username);
}
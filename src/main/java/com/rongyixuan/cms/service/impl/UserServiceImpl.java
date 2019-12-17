/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: UserServiceImpl.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.service.impl 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月20日 下午2:23:51 
 * @version: V1.0   
 */
package com.rongyixuan.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rongyixuan.cms.dao.UserMapper;
import com.rongyixuan.cms.domain.User;
import com.rongyixuan.cms.service.UserService;
import com.rongyixuan.cms.utils.CMSException;
import com.rongyixuan.cms.utils.Md5Util;
import com.rongyixuan.cms.vo.UserVO;
import com.xuanxuan.common.StringUtil;

/** 
 * @ClassName: UserServiceImpl 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月20日 下午2:23:51  
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserMapper userMapper ;
	
	/* 用户全查加分页(non Javadoc) 
	 * @Title: selects
	 * @Description: TODO
	 * @param user
	 * @param page
	 * @param pageSize
	 * @return 
	 * @see com.rongyixuan.cms.service.UserService#selects(com.rongyixuan.cms.domain.User, java.lang.Integer, java.lang.Integer) 
	 */
	@Override
	public PageInfo<User> selects(User user, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<User> users = userMapper.selects(user);
		PageInfo<User> info = new PageInfo<User>(users);
		return info;
	}
	/* 用户的修改(non Javadoc) 
	 * @Title: update
	 * @Description: TODO
	 * @param user
	 * @return 
	 * @see com.rongyixuan.cms.service.UserService#update(com.rongyixuan.cms.domain.User) 
	 */
	@Override
	public boolean update(User user) {
		try {
			return userMapper.updateByPrimaryKeySelective(user)>0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("操作失败");
		}
	}
	/* 用户登录(non Javadoc) 
	 * @Title: login
	 * @Description: TODO
	 * @param user
	 * @return 
	 * @see com.rongyixuan.cms.service.UserService#login(com.rongyixuan.cms.domain.User) 
	 */
	@Override
	public User login(User user) {

		//判断登录注册信息是否满足要求
		if(!StringUtil.hasText(user.getUsername()))
		 throw new CMSException("用户名不能为空");	
		if(!StringUtil.hasText(user.getPassword()))
		 throw new CMSException("密码不能为空");	
		//查询用户名是否存在
		User u = userMapper.selectByName(user.getUsername());
		if(null==u)
			 throw new CMSException("用户名不存在");
		else if(u.getLocked()==1) {
			throw new CMSException("账户被禁用!");
		}
		else if(!Md5Util.md5Encoding(user.getPassword()).equals(u.getPassword())) {
			throw new CMSException("密码错误!");	
		}
		return u;
	}
	
	/* 判断注册是否合格，后台校验
	 * (non Javadoc) 
	 * @Title: insertSelective
	 * @Description: TODO
	 * @param userVO
	 * @return 
	 * @see com.rongyixuan.cms.service.UserService#insertSelective(com.rongyixuan.cms.vo.UserVO) 
	 */
	@Override
	public boolean insertSelective(UserVO userVO) {
		//判断注册信息是否满足要求
		if(!StringUtil.hasText(userVO.getUsername()))
		 throw new CMSException("用户名不能为空");	
		if(!StringUtil.hasText(userVO.getPassword()))
		 throw new CMSException("密码不能为空");	
		if(!StringUtil.hasText(userVO.getRepassword()))
			 throw new CMSException("确认密码不能为空");
		if(!userVO.getPassword().equals(userVO.getRepassword()))
			 throw new CMSException("两次密码不一致");
		//用户名不能重复注册
		User u = userMapper.selectByName(userVO.getUsername());
		if(null!=u)
			 throw new CMSException("用户名不能重复注册");
		
		//执行注册
		//对密码进行加密保存
		userVO.setPassword(Md5Util.md5Encoding(userVO.getPassword()));
		
		//用户注册的其他属性默认值
		userVO.setCreated(new Date());//注册时间
		userVO.setNickname(userVO.getUsername());//昵称
		
		return userMapper.insertSelective(userVO)>0;
	}

}

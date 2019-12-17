/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: PassportController.java 
 * @Prject: xuan-cms
 * @Package: com.rongyixuan.cms.controller 
 * @Description: TODO
 * @author: 86155   
 * @date: 2019年11月21日 下午3:21:05 
 * @version: V1.0   
 */
package com.rongyixuan.cms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rongyixuan.cms.domain.User;
import com.rongyixuan.cms.service.UserService;
import com.rongyixuan.cms.utils.CMSException;
import com.rongyixuan.cms.vo.UserVO;

/** 
 * @ClassName: PassportController 
 * @Description: TODO
 * @author: 86155
 * @date: 2019年11月21日 下午3:21:05  
 */
@RequestMapping("passport")
@Controller
public class PassportController {
	@Resource
	private UserService userService;
	
	
	/**
	 * 注销退出
	 * @Title: logout 
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: String
	 */
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(null!=session)
		session.invalidate();
		return "redirect:/passport/login";
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 去登录页面
	 * @return
	 * @return: String
	 */
	
	@GetMapping("login")
	public String login() {
		return "passport/login";
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 用户登录
	 * @param user
	 * @return
	 * @return: String
	 */
	@PostMapping("login")
	public String login(Model model,User user,HttpSession session) {
		try {
			User u = userService.login(user);
			
			if(u.getRole().equals("1")) {//1:管理员 0:普通用户
				session.setAttribute("admin", u);
				return "redirect:/admin";//管理员进入管理员后台
			}else {
				session.setAttribute("user", u);
				return "redirect:/my";//普通注册进入个人中心
			}
		} catch (CMSException e) {
			e.printStackTrace();
			//封装到model,用于提示用户
			model.addAttribute("message", e.getMessage());
		}catch (Exception e) {
			model.addAttribute("message", "系统异常.请联系管理员");
		}
		return "passport/login";
	}
	
	
	/**
	 * 
	 * @Title: reg 
	 * @Description: 去注册页面
	 * @return
	 * @return: String
	 */
	
	@GetMapping("reg")
	public String reg() {
		return "passport/reg";
	}
	
	/**
	 * 真正注册用户
	 * @Title: reg 
	 * @Description: TODO
	 * @param model
	 * @param userVO
	 * @param redirectAttributes
	 * @return
	 * @return: String
	 */
	@PostMapping("reg")
	public String reg(Model model ,UserVO userVO,RedirectAttributes redirectAttributes) {
		
			try {
			boolean b =userService.insertSelective(userVO);	
			if(b) {
				redirectAttributes.addFlashAttribute("username", userVO.getUsername());
				redirectAttributes.addFlashAttribute("message", "恭喜,注册成功");
				
				return "redirect:/passport/login";//注册成功,重定向到登录页面
			}
		}catch (CMSException e) {
			e.printStackTrace();
			//封装到model,用于提示用户
			model.addAttribute("message", e.getMessage());
		}catch (Exception e) {
			model.addAttribute("message", "系统异常.请联系管理员");
		}
		
		return "passport/reg";//注册失败
	}
	
	
}

package com.qm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qm.pojo.User;
import com.qm.service.Userservice;
import com.qm.util.ActionResult;
import com.qm.util.SessionMap;

/**
 * 负责用户注册、登录、注销controller
 * @author DBC
 *
 */

@Controller
public class LoginController {
	
	
	@Autowired
	private Userservice userService;
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	public ActionResult registerUser(User user,@RequestParam("password") String password
			,HttpServletRequest request,@RequestParam("code") String code) {
		String c = (String) request.getSession().getAttribute("code");
		if(code == null || !code.equalsIgnoreCase(c)){
			return ActionResult.build(303, "验证码错误");
		}
		try {
			user.setPwd(password);
			userService.registerUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ActionResult.build(400, "注册失败");
		}
		return ActionResult.Ok("注册成功");
	}
	
	
	/**
	 * 检查用户名或密码是否正确
	 * @param username
	 * @param pwd
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public ActionResult checkUser(@RequestParam("username") String username,
								@RequestParam("password") String pwd,
								HttpServletRequest request,@RequestParam("code") String code,
								boolean isSavePwd) {
		System.out.println(isSavePwd);
		String c = (String) request.getSession().getAttribute("code");
		if(code == null || !code.equalsIgnoreCase(c)){
			return ActionResult.build(303, "验证码错误");
		}
		ActionResult result = userService.login(username, pwd);
		if(result.getStatusCode() == 200) {
			User user = (User) result.getResult();
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			List<User> list = userService.getFriendsById(user.getId());
			session.setAttribute("users", list);
			SessionMap.getInstances().setSession(session);
		}
		return result;
	}
	
	
	/**
	 * 检查用户名是否存在
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/check",method=RequestMethod.GET)
	@ResponseBody
	public ActionResult checkName(@RequestParam("username") String username) {
		boolean isExist = userService.checkNameIsExist(username);
		return ActionResult.Ok(isExist);
	}
	
}

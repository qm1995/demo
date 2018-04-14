package com.dragonsoftbravo.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dragonsoftbravo.pojo.User;
import com.dragonsoftbravo.service.Userservice;
import com.dragonsoftbravo.util.ActionResult;

/**
 * 用户controller
 * @author DBC
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private Userservice userService;
	
	
	/**
	 * 根据主id寻找对应的friends
	 * @param request
	 * @return
	 */
	@RequestMapping("/getFriends")
	@ResponseBody
	public List<User> getFriends(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		Integer id = user.getId();
		List<User> friends = userService.getFriendsById(id);
		session.setAttribute("friends", friends);
		return friends;
	}
	
	
	/**
	 * 添加好友
	 * @param uid
	 * @param fid
	 * @return
	 */
	@RequestMapping(value="/addFriends",method=RequestMethod.GET)
	@ResponseBody
	public ActionResult addFriends(@RequestParam("fromUser") Integer uid,@RequestParam("toUser") Integer fid){
		if(uid == fid){//此时表示添加自己为好友，直接否决
			return ActionResult.Ok(false);
		}
		boolean isSuccess = userService.addFriend(uid, fid);
		
		return ActionResult.Ok(isSuccess);
	}
	
	/**
	 * 根据用户名寻找用户
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/findUser",method=RequestMethod.GET)
	@ResponseBody
	public ActionResult findUserByName(@RequestParam("name") String username){
		List<User> list = userService.findUsersByName(username);
		return ActionResult.Ok(list);
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		Object object = session.getAttribute("user");
		if(object == null){//说明不是通过正常注销的路径
			return "redirect:/page/index.html";
		}
		User u = (User) object;
		userService.logout(u);
		session.removeAttribute("user");
		return "redirect:/page/index.html";
	}
}

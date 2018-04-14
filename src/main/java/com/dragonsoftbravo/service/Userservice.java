package com.dragonsoftbravo.service;

import java.util.List;

import com.dragonsoftbravo.pojo.User;
import com.dragonsoftbravo.util.ActionResult;

/**
 * 用来操作用户的service层
 * @author qiumin
 *
 */
public interface Userservice {

	/**
	 * 检查用户名和密码,登录
	 * @param username
	 * @param pwd
	 * @return
	 */
	ActionResult login(String username, String pwd);

	/**
	 * 注册用户
	 * @param user
	 */
	void registerUser(User user);

	/**
	 * 检查用户名是否存在
	 * @param username
	 * @return
	 */
	boolean checkNameIsExist(String username);
	
	/**
	 * 根据主id得到好友
	 * @param uid
	 * @return
	 */
	List<User> getFriendsById(Integer uid);
	
	/**
	 * 根据用户名查询出用户信息
	 * @param username
	 * @return
	 */
	List<User> findUsersByName(String username);
	
	/**
	 * 添加成好友关系
	 * @param uid
	 * @param fid
	 * @return
	 */
	boolean addFriend(Integer uid,Integer fid);
	
	/**
	 * 用户注销
	 * @param uid
	 * @return
	 */
	boolean logout(User user);
}
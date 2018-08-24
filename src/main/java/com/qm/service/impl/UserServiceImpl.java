package com.qm.service.impl;

import java.util.Date;
import java.util.List;

import com.qm.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.qm.mapper.FriendUserMapper;
import com.qm.mapper.UserMapper;
import com.qm.pojo.FriendUserExample;
import com.qm.pojo.FriendUserKey;
import com.qm.pojo.User;
import com.qm.pojo.UserExample;
import com.qm.pojo.UserExample.Criteria;
import com.qm.util.ActionResult;

@Service
public class UserServiceImpl implements Userservice {
	
	@Autowired
	private UserMapper userMapper;
	
	
	@Autowired
	private FriendUserMapper friendMapper;
	
	@Override
	public ActionResult login(String username,String pwd) {
		String md5Pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPwdEqualTo(md5Pwd);
		List<User> users = userMapper.selectByExample(example);
		if(users == null || users.size() != 1) {
			return ActionResult.build(400, "用户名或密码错误");
		}
		User user = users.get(0);
		updateLoginState(user, 1);
		return ActionResult.Ok(users.get(0));
	}
	
	@Override
	public void registerUser(User user) {
		user.setPwd(DigestUtils.md5DigestAsHex(user.getPwd().getBytes()));
		user.setIslogin(0);
		user.setLogindate(new Date());
		user.setRegisterdate(new Date());
		userMapper.insertSelective(user);
		return;
	}
	
	@Override
	public boolean checkNameIsExist(String username) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> list = userMapper.selectByExample(example);
		if(list.size() == 0 || list == null) {
			return false;
		}
		return true;
	}
	
	
	@Override
	public List<User> getFriendsById(Integer uid){
		List<User> friendList = userMapper.findFriendsById(uid);
		return friendList;
	}

	@Override
	public List<User> findUsersByName(String username) {
		// TODO Auto-generated method stub
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameLike(username);
		List<User> list = userMapper.selectByExample(example);
		
		return list;
	}

	@Override
	public boolean addFriend(Integer uid, Integer fid) {
		// TODO Auto-generated method stub
		FriendUserExample example = new FriendUserExample();
		com.qm.pojo.FriendUserExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andFidEqualTo(fid);
		createCriteria.andUidEqualTo(uid);
		List<FriendUserKey> list = friendMapper.selectByExample(example);
		if(list.size() > 0){
			return false;//此时已经存在好友关系
		}
		FriendUserKey record = new FriendUserKey();
		record.setFid(fid);
		record.setUid(uid);
		int insert = friendMapper.insert(record);
		if(insert == 1){
			return true;
		}
		return false;
	}

	@Override
	public boolean logout(User user) {
		// TODO Auto-generated method stub
		return updateLoginState(user, 0);
	}
	
	/**
	 * 私有的方法，
	 * 用于更新用户的状态
	 * @param uid
	 * @return
	 */
	private boolean updateLoginState(User user,Integer state){
		user.setIslogin(state);
		int i = userMapper.updateByPrimaryKey(user);
		return i==1;
	}
}

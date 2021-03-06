package com.qm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qm.pojo.User;
import com.qm.pojo.UserExample;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> findFriendsById(Integer uid);

    List<User> findByIds(@Param("uid") Integer id1,@Param("fid") Integer id2);
}
package com.qm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qm.pojo.FriendUserExample;
import com.qm.pojo.FriendUserKey;

public interface FriendUserMapper {
    int countByExample(FriendUserExample example);

    int deleteByExample(FriendUserExample example);

    int deleteByPrimaryKey(FriendUserKey key);

    int insert(FriendUserKey record);

    int insertSelective(FriendUserKey record);

    List<FriendUserKey> selectByExample(FriendUserExample example);

    int updateByExampleSelective(@Param("record") FriendUserKey record, @Param("example") FriendUserExample example);

    int updateByExample(@Param("record") FriendUserKey record, @Param("example") FriendUserExample example);
}
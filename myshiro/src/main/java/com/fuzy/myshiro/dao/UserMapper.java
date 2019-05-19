package com.fuzy.myshiro.dao;

import com.fuzy.myshiro.pojo.User;
import com.fuzy.myshiro.pojo.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fuzy
 */
public interface UserMapper {
    long countByExample(UserExample example);

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
}
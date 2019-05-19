package com.fuzy.myshiro.dao;

import com.fuzy.myshiro.pojo.Module;
import com.fuzy.myshiro.pojo.ModuleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fuzy
 */
public interface ModuleMapper {
    long countByExample(ModuleExample example);

    int deleteByExample(ModuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Module record);

    int insertSelective(Module record);

    List<Module> selectByExample(ModuleExample example);

    Module selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByExample(@Param("record") Module record, @Param("example") ModuleExample example);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
}
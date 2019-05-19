package com.fuzy.myshiro.dao;

import com.fuzy.myshiro.pojo.ModuleRole;
import com.fuzy.myshiro.pojo.ModuleRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author fuzy
 */
public interface ModuleRoleMapper {
    long countByExample(ModuleRoleExample example);

    int deleteByExample(ModuleRoleExample example);

    int insert(ModuleRole record);

    int insertSelective(ModuleRole record);

    List<ModuleRole> selectByExample(ModuleRoleExample example);

    int updateByExampleSelective(@Param("record") ModuleRole record, @Param("example") ModuleRoleExample example);

    int updateByExample(@Param("record") ModuleRole record, @Param("example") ModuleRoleExample example);
}
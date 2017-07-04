package org.fictio.shop.ijjg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.fictio.shop.ijjg.pojo.Good;

public interface GoodMapper {
    int insert(Good record);

    int insertSelective(Good record);
    
    Good getGoodById(int id);
    
    List<Good> getGoodByPage(@Param("pageStart")int pageStart,@Param("pageSize")int pageSize);
}
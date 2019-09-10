package com.itdr.mappers;


import com.itdr.pojo.Categoey;

public interface CategoeyMapper {
    int deleteByPrimaryKey ( Integer id );

    int insert ( Categoey record );

    int insertSelective ( Categoey record );

    Categoey selectByPrimaryKey ( Integer id );

    int updateByPrimaryKeySelective ( Categoey record );

    int updateByPrimaryKey ( Categoey record );
}
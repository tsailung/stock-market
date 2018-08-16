package com.metapro.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SequenceMapper {

    @Select("SELECT SELECT_NEXT_SEQ_VAL(#{name}) FROM DUAL")
    Integer nextSequence(@Param("name") String name);
}

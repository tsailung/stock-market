package com.metapro.stock.mapper;

import com.metapro.stock.entity.StockQuote;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockQuoteMapper {
    int insert(StockQuote record);

    int insertSelective(StockQuote record);

    StockQuote selectByPrimaryKey(Integer id);
}
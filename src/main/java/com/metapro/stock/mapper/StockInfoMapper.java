package com.metapro.stock.mapper;

import com.metapro.stock.entity.StockInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockInfoMapper {
    int insert(StockInfo record);

    int insertSelective(StockInfo record);

    StockInfo selectStockInfoByCode(String Code);

    List<StockInfo> selectStockInfoList();

}
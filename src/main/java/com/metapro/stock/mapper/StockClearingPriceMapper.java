package com.metapro.stock.mapper;

import com.metapro.stock.entity.StockClearingPrice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockClearingPriceMapper {
    int insert(StockClearingPrice record);

    int insertSelective(StockClearingPrice record);

    @Insert("insert into t_stock_clearing_price_${code} (name, code, clr_date, clr_price) " +
            "values (#{name}, #{code}, #{clrDate}, #{clrPrice})")
    int insertStockClearingPric(StockClearingPrice record);

    StockClearingPrice selectByPrimaryKey(Integer id);
}
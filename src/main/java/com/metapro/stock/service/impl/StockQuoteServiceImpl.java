package com.metapro.stock.service.impl;

import com.metapro.stock.entity.StockClearingPrice;
import com.metapro.stock.entity.StockInfo;
import com.metapro.stock.mapper.StockClearingPriceMapper;
import com.metapro.stock.mapper.StockInfoMapper;
import com.metapro.stock.service.ServiceException;
import com.metapro.stock.service.StockQuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StockQuoteServiceImpl implements StockQuoteService {
    private final Logger                logger = LoggerFactory.getLogger(StockQuoteService.class);

    @Resource
    private StockClearingPriceMapper    stockClearingPriceMapper;
    @Resource
    private StockInfoMapper             stockInfoMapper;

    @Override
    public StockInfo selectStockInfoByCode(String code){
        try {
            return stockInfoMapper.selectStockInfoByCode(code);
        } catch (Exception e) {
            logger.error("==== 获取股票信息失败：", e);
            return null;
        }
    }

    @Override
    public List<StockInfo> selectStockInfoList(){
        try {
            return stockInfoMapper.selectStockInfoList();
        } catch (Exception e) {
            logger.error("==== 获取所有的股票信息失败：", e);
            return null;
        }
    }

    @Override
    public void saveStockClearingPric(StockClearingPrice clearingPrice) throws ServiceException {
        try {
            stockClearingPriceMapper.insertSelective(clearingPrice);
        } catch (Exception e) {
            logger.error("==== 保存股票昨收价异常：", e);
        }
    }
}

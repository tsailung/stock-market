package com.metapro.stock.service;

import com.metapro.stock.entity.StockClearingPrice;
import com.metapro.stock.entity.StockInfo;

import java.util.List;

public interface StockQuoteService {

    void saveStockClearingPric(StockClearingPrice clearingPrice) throws ServiceException;

    StockInfo selectStockInfoByCode(String code) throws ServiceException;

    List<StockInfo> selectStockInfoList() throws ServiceException;
}

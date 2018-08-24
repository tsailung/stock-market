package com.metapro.stock;

import com.alibaba.fastjson.JSON;
import com.metapro.stock.entity.StockClearingPrice;
import com.metapro.stock.entity.StockInfo;
import com.metapro.stock.entity.StockQuote;
import com.metapro.stock.service.GatewayService;
import com.metapro.stock.service.ServiceException;
import com.metapro.stock.service.StockQuoteService;
import com.metapro.stock.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
@Lazy(false)
@EnableScheduling
public class TaskScheduleEntrance {

    private final Logger logger = LoggerFactory.getLogger(TaskScheduleEntrance.class);

    @Autowired
    private StockQuoteService stockQuoteService;
    @Autowired
    private GatewayService gatewayService;


//    @Scheduled(cron = "00 54 12 * * ?")
    public void selectClearingPrice() {
        try {
            List<StockInfo> stockList = stockQuoteService.selectStockInfoList();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2015);
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            System.out.println(Constants.DTFORMATT_LINE.format(calendar.getTime()));

            for (StockInfo info : stockList) {
                String code = info.getCode();
                String name = info.getName();
                while (!Constants.DTFORMATT_EMPTY.format(calendar.getTime())
                        .equals(Constants.DTFORMATT_EMPTY.format(Calendar.getInstance().getTime()))) {
                    try {
                        int week = calendar.get(Calendar.DAY_OF_WEEK);
                        if (week == 1 || week == 7) continue;
                        String date = Constants.DTFORMATT_LINE.format(calendar.getTime());
                        BigDecimal data = gatewayService.requestClearingPrice(date, date, code);
                        if (data != null) {
                            StockClearingPrice clsPri = new StockClearingPrice();
                            clsPri.setClrDate(calendar.getTime());
                            clsPri.setClrPrice(data);
                            clsPri.setCode(code);
                            clsPri.setName(name);
                            stockQuoteService.saveStockClearingPric(clsPri);
                        }
                    } catch (Exception e) {
                        logger.error("---- ", e);
                    } finally {
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                    }
                }

            }
        } catch (ServiceException e) {
            logger.error("", e);
        }
    }

    @Scheduled(cron = "00 00 16 * * ?")
    public void acquireCurrentQuote(){
        try {
            List<StockInfo> stockList = stockQuoteService.selectStockInfoList();
            stockList.forEach(item -> {
                String url = "http://web.juhe.cn:8080/finance/stock/hs?gid=sz002230&key=1534c8df285b90e949be0d62c19cc6a0";
                String data = HttpClientUtil.doGet(url, "UTF-8");
                JSON.parseObject(data, StockQuote.class);
                System.out.println(data);
            });
        } catch (ServiceException e) {
            logger.error("", e);
        }
    }
}

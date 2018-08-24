package com.metapro.stock;

import com.metapro.stock.entity.StockClearingPrice;
import com.metapro.stock.entity.StockInfo;
import com.metapro.stock.service.GatewayService;
import com.metapro.stock.service.ServiceException;
import com.metapro.stock.service.StockQuoteService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class},
		scanBasePackages = {"com.metapro.stock"})
@MapperScan(basePackages = "com.metapro.stock.mapper")
public class StockMarketApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(StockMarketApplication.class, args);
	}
}

@Slf4j
//@Component
class StockRunner implements CommandLineRunner{

	@Autowired
	private StockQuoteService stockQuoteService;
	@Autowired
	private GatewayService gatewayService;

	@Override
	public void run(String... args) throws Exception {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2015);
			calendar.set(Calendar.MONTH, Calendar.DECEMBER);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			System.out.println(Constants.DTFORMATT_LINE.format(calendar.getTime()));

			StockInfo stockInfo = new StockInfo();
			stockInfo.setCode("601398");
			stockInfo.setName("工商银行");
			List<StockInfo> stockList = new ArrayList<>();
			stockList.add(stockInfo);
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
						log.error("---- ", e);
					} finally {
						calendar.add(Calendar.DAY_OF_MONTH, 1);
					}
				}

			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
}

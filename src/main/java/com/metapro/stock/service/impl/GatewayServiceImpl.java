package com.metapro.stock.service.impl;

import com.alibaba.fastjson.JSON;
import com.metapro.stock.Constants;
import com.metapro.stock.mapper.SequenceMapper;
import com.metapro.stock.service.GatewayService;
import com.metapro.stock.utils.HttpClientUtil;
import com.metapro.stock.utils.RegexUtils;
import com.metapro.stock.vo.CMSClearingPrice;
import com.metapro.stock.vo.CMSResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Component
public class GatewayServiceImpl implements GatewayService {

    private final Logger logger = LoggerFactory.getLogger(GatewayServiceImpl.class);

    private final String STOCKSTAR_HOST = "http://stock.quote.stockstar.com/002230.shtml";
    private final String STOCKSTAR_CAPITAL_HOST = "http://stock.quote.stockstar.com/capital_002230.shtml";

    @Resource
    private SequenceMapper sequenceMapper;

    public void requestStockstar(){
        logger.info("==========================================================");
        String original_data = HttpClientUtil.doGet(STOCKSTAR_HOST, "gbk");
        String capital_data = HttpClientUtil.doGet(STOCKSTAR_CAPITAL_HOST, "gbk");
        System.out.println(capital_data);
        String quote_data = RegexUtils.matcherQuote(original_data);
        System.out.println(quote_data);

        String jk = RegexUtils.matcherQuoteJK(quote_data);
        String zs = RegexUtils.matcherQuoteZS(quote_data);
        String zg = RegexUtils.matcherQuoteZG(quote_data);
        String zd = RegexUtils.matcherQuoteZD(quote_data);
        System.out.println(jk);
        System.out.println(zs);
        System.out.println(zg);
        System.out.println(zd);

        Integer seq_test = sequenceMapper.nextSequence("seq_test");
        System.out.println(seq_test);
        logger.info("==========================================================");
    }

    public BigDecimal requestClearingPrice(String dateStart, String dateEnd, String code){
        String host = String.format(Constants.STOCK_CLEARING_PRICE_HOST, dateStart, dateEnd, code);
        logger.info("-------- REQUEST_HOST: " + host);
        String data = HttpClientUtil.doGet(host);
        logger.info("-------- RESPONSE_DATA: " + data);
        if(StringUtils.isBlank(data)) return null;
        CMSResponse response = JSON.parseObject(data, CMSResponse.class);
        List<CMSClearingPrice> prices = response.getData();
        if(prices != null && prices.size() > 0) {
            return new BigDecimal(prices.get(0).getClsPrc());
        }
        return null;
    }

}

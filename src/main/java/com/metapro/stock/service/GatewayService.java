package com.metapro.stock.service;

import java.math.BigDecimal;

public interface GatewayService {

    BigDecimal requestClearingPrice(String dateStart, String dateEnd, String code) throws ServiceException;

}

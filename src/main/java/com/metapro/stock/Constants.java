package com.metapro.stock;

import java.text.SimpleDateFormat;

public class Constants {

    public final static String              STOCK_CLEARING_PRICE_HOST = "https://zszx.cmschina.com/pcnews/f10/smtcnexchtrddet?dateStart=%1$s&dateEnd=%2$s&scode=%3$s&ecode=0";

    public final static SimpleDateFormat    DTFORMATT_LINE = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat    DTFORMATT_EMPTY = new SimpleDateFormat("yyyyMMdd");

    public final static String              DELAY_EVENT_ROUTE_KEY = "testDelayEvent4";
    public final static String              IMMEDIATELY_EVENT_ROUTE_KEY = "testImmediatelyEvent";
}

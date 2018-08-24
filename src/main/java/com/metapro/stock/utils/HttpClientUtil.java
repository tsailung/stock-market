package com.metapro.stock.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.metapro.stock.entity.StockQuote;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

import java.util.*;

public class HttpClientUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String doGet(String url) {
        return doGet(url, "UTF-8");
    }

    public static String doGet(String url, String defaultCharset) {
        String result = null;
        try(CloseableHttpClient httpclient = HttpClientFactory.createHttpClient()) {
            Charset charset = StringUtils.isBlank(defaultCharset) ?
                    Consts.UTF_8 : Charset.forName(defaultCharset);
            HttpGet httpget = new HttpGet(url);

            logger.info("---- executing request " + httpget.getURI());

            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                if (response != null) {
                    HttpEntity resEntity = response.getEntity();
                    if (resEntity != null) {
                        result = EntityUtils.toString(resEntity, charset);
                    }
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String doPost(String url, Map<String, String> param){
        try (CloseableHttpClient httpclient = HttpClientFactory.createHttpClient()) {
            HttpPost httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<>();
            Iterator iterator = param.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, Consts.UTF_8);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpclient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    return EntityUtils.toString(resEntity, Consts.UTF_8);
                }
            }
        } catch (Exception ex) {
            logger.error("请求网关异常：", ex);
        }
        return StringUtils.EMPTY;
    }


    private static String url = "http://ff.eastmoney.com/EM_CapitalFlowInterface/api/js?id=0022302&type=hff&rtntype=2&cb=var%20aff_data=&js=(x)&check=TMLBMS&acces_token=4f1862fc3b5e77c150a2b985b12db0fd&_=1533778407000";



    public static void main(String[] a) {
        String data = doGet(url, "UTF-8");
        System.out.println(data);
        url = "http://nuff.eastmoney.com/EM_Finance2015TradeInterface/JS.ashx?id=0022302&token=4f1862fc3b5e77c150a2b985b12db0fd&cb=jQuery18304692765985979732_1533779705225&_=" + System.currentTimeMillis();
        data = doGet(url, "UTF-8");
        System.out.println(data);

        url = "http://mdfm.eastmoney.com/EM_UBG_MinuteApi/Js/Get?dtype=25&style=tail&check=st&dtformat=HH:mm:ss&cb=jQuery18300945849766746174_1533778624578&id=0022302&num=10&_=" + System.currentTimeMillis();
        data = doGet(url, "UTF-8");
        System.out.println(data);

        url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=0022302&sty=HC&st=z&sr=&p=&ps=&cb=&js=var%20bjTime=(x)&token=7bc05d0d4c3c22ef9fca8c2a912d779c&_=1533783641456";
        data = doGet(url, "UTF-8");
        System.out.println(data);

        //1533783713460
        url = "http://mdfm.eastmoney.com/EM_UBG_MinuteApi/Js/Get?dtype=25&style=tail&check=st&dtformat=HH:mm:ss&cb=jQuery183006179988097715472_1533783581434&id=0022302&num=10&_=" + System.currentTimeMillis();
        data = doGet(url, "UTF-8");
        System.out.println(data);

        url = "http://pdfm.eastmoney.com/EM_UBG_PDTI_Fast/api/js?rtntype=5&token=4f1862fc3b5e77c150a2b985b12db0fd&cb=jQuery183006179988097715472_1533783581430&id=0022302&type=r&iscr=false&_=" + System.currentTimeMillis();
        data = doGet(url, "UTF-8");
        System.out.println(data);

        url = "http://nuyd.eastmoney.com/EM_UBG_PositionChangesInterface/api/js?style=top&js=([(x)])&ac=normal&check=itntcd&cb=jQuery183006179988097715472_1533783581430&id=0022302&_=" + System.currentTimeMillis();
        data = doGet(url, "UTF-8");
        System.out.println(data);

        url = "http://web.juhe.cn:8080/finance/stock/hs?gid=sz002230&key=1534c8df285b90e949be0d62c19cc6a0";
        data = doGet(url, "UTF-8");
        Map<String, Object> map = JSONObject.parseObject(data, Map.class);
        JSONArray arr = (JSONArray) map.get("result");
        StockQuote stockQuote = JSON.parseObject(((JSONObject)arr.get(0)).get("data").toString(), StockQuote.class);
        System.out.println(stockQuote);
        System.out.println(data);

        //https://hq.sinajs.cn/?_=0.48335311859046626&list=sz002230,sz002230_i

        url = "https://hq.sinajs.cn/?_=0.6710722542621561&list=sz002230";
        data = doGet(url, "UTF-8");
        System.out.println(data);


        url = "http://vip.stock.finance.sina.com.cn/quotes_service/api/jsonp.php/var%20moneyFlowData=/MoneyFlow.ssi_ssfx_flzjtj?daima=sz002230&gettime=1";
        data = doGet(url, "gbk");
        System.out.println(data);

        //https://zszx.cmschina.com/pcnews/f10/smtcnexchtrddet?dateStart=2018-08-06&dateEnd=2018-08-06&scode=002230&ecode=0







        // 2018-08-09,16.7148,343.8998,-327.185,120.1814,-136.8963
        // 2018-08-09,-52.7533,343.8998,-396.6531,60.2317,-7.4783
    }

}

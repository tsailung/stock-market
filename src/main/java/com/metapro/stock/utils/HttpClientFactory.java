package com.metapro.stock.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

public class HttpClientFactory {

    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(15 * 1000)
            .setConnectTimeout(15 * 1000)
            .setSocketTimeout(15 * 1000)
            .build();
    private static final ConnectionConfig connConfig = ConnectionConfig.custom()
            .setCharset(Consts.UTF_8)
            .build();
    private static final ConnectionKeepAliveStrategy keepAliveStrategy =
            new DefaultConnectionKeepAliveStrategy() {
                //                                                                new ConnectionKeepAliveStrategy(){
                @Override
                public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                    long keepAlive = super.getKeepAliveDuration(response, context);
//                                                                        HeaderElementIterator iterator = new BasicHeaderElementIterator(
//                                                                                                                response.headerIterator(HTTP.CONN_KEEP_ALIVE));
//                                                                        while (iterator.hasNext()) {
//                                                                            HeaderElement he = iterator.nextElement();
//                                                                            String param = he.getName();
//                                                                            String value = he.getValue();
//                                                                            if(value != null && "timeout".equalsIgnoreCase(param)) {
//                                                                                return Long.parseLong(value) * 1000;
//                                                                            }
//                                                                        }
//                                                                        HttpHost host = (HttpHost) context.getAttribute(HttpClientContext.HTTP_TARGET_HOST);
//                                                                        if("www.host.com".equalsIgnoreCase(host.getHostName())) {
//                                                                            //keep alive for 5 seconds only
//                                                                            return 5 * 1000;
//                                                                        }else {
//                                                                            // otherwise keep alive for 10 seconds
//                                                                            return 10 * 1000;
//                                                                        }
                    if (keepAlive == -1) {
                        keepAlive = 10 * 1000;
                    }
                    return keepAlive;
                }
            };
    private static final HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {

        public boolean retryRequest(IOException exception, int executionCount, HttpContext httpContext) {
            if (executionCount >= 3) {
                // don't retry if over max retry count
                return false;
            }
            if (exception instanceof UnknownHostException) {
                //unknown host
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {
                return false;
            }
            if (exception instanceof SSLException) {
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(httpContext);
            HttpRequest request = clientContext.getRequest();
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            if (idempotent) {
                // retry if the request is considered idempotent.
                return true;
            }
            return false;
        }
    };

    public static CloseableHttpClient createHttpClient(){
        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setDefaultConnectionConfig(connConfig)
                .setKeepAliveStrategy(keepAliveStrategy)
                .setRetryHandler(retryHandler)
                .build();
    }


}

package com.metapro.stock.mq;

public interface MQService {
    void send(String msg, String routeKey);

    void send(String msg, String exchange, String routeKey);

    void sendWithDelay(String msg, String routeKey, int seconds);
}

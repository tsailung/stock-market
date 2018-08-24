package com.metapro.stock.mq.listener;

import com.alibaba.fastjson.JSON;
import com.metapro.stock.Constants;
import com.metapro.stock.config.MQPropConfiguration;
import com.metapro.stock.dto.MQTransferDataDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class MQConsumer {
    @Resource
    private MQPropConfiguration mqPropConfig;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 配置消息队列-消费事件 针对消费者配置
     *
     * @return
     */
    @Bean
    public Queue queueTestDelayBizEvent() {
        return new Queue(Constants.DELAY_EVENT_ROUTE_KEY, true); // 队列持久
    }

    /**
     * 配置消息队列-消费事件 针对消费者配置
     *
     * @return
     */
    @Bean
    public Queue queueTestImmediatelyBizEvent() {
        return new Queue(Constants.IMMEDIATELY_EVENT_ROUTE_KEY, true); // 队列持久
    }

    /**
     * 将消费事件消息队列与交换机绑定 针对消费者配置
     *
     * @return
     */
    @Bean
    public Binding bindingDelayBizEvent() {
        return BindingBuilder.bind(queueTestDelayBizEvent())
                .to(mqPropConfig.delayExchange())
                .with(Constants.DELAY_EVENT_ROUTE_KEY).noargs();
    }

    /**
     * 将消费事件消息队列与交换机绑定 针对消费者配置
     *
     * @return
     */
    @Bean
    public Binding bindingImmediatelyBizEvent() {
        return BindingBuilder.bind(queueTestImmediatelyBizEvent())
                .to(mqPropConfig.defaultExchange())
                .with(Constants.IMMEDIATELY_EVENT_ROUTE_KEY);
    }

    @Bean
    public SimpleMessageListenerContainer consumerImmediatelyMealEvent() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(mqPropConfig.connectionFactory());
        container.setQueues(queueTestImmediatelyBizEvent());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(2);
        container.setConcurrentConsumers(2);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置确认模式手工确认
        container.setChannelAwareMessageListener((message, channel) -> {
            try {
                Date now = new Date();
                byte[] body = message.getBody();
                String content = new String(body);
                log.info("即时-收到消息内容: {} ", content);
                log.info("即时-发送时间：{}，收到时间：{}",
                        dateFormat.format(JSON.parseObject(content, MQTransferDataDto.class).getSendTime()),
                        dateFormat.format(now));
                //TODO
                log.info("即时-消息消费成功 {} ", content);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // 确认消息成功消费
            } catch (IOException e) {
                log.error("-------------即时-消费异常：", e);
            }
        });
        return container;
    }


    @Bean
    public SimpleMessageListenerContainer consumerDelayMealEvent() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(mqPropConfig.connectionFactory());
        container.setQueues(queueTestDelayBizEvent());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(2);
        container.setConcurrentConsumers(2);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置确认模式手工确认
        container.setChannelAwareMessageListener((message, channel) -> {
            try {
                Date now = new Date();
                byte[] body = message.getBody();
                String content = new String(body);
                log.info("延时-收到消息内容: {} ", content);
                log.info("延时-发送时间：{}，收到时间：{}",
                        dateFormat.format(JSON.parseObject(content, MQTransferDataDto.class).getSendTime()),
                        dateFormat.format(now));
                //TODO
                log.info("延时-消息消费成功 {} ", content);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // 确认消息成功消费
            } catch (IOException e) {
                log.error("-------------延时-消费异常：", e);
            }
        });
        return container;
    }
}

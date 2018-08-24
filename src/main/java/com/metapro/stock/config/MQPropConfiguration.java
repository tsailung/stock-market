package com.metapro.stock.config;


import com.metapro.stock.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(
        prefix = "spring.rabbitmq"
)
public class MQPropConfiguration {

    private String host;
    private int port;
    private String userName;
    private String password;
    private String virtualHost;
    private Boolean publisherConfirm;
    private int cacheSize;
    private String exDefault;
    private String exDelay;
    private Integer delayTimeDefault;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(this.host, this.port);
        connectionFactory.setUsername(this.userName);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.virtualHost);
        connectionFactory.setChannelCacheSize(this.cacheSize);
        connectionFactory.setPublisherConfirms(this.publisherConfirm);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(this.connectionFactory());
        return rabbitTemplate;
    }

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(this.exDefault, true, false);
    }

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("test_exchange", "x-delayed-message", true, false, args);
    }

    @Bean
    public Queue testQueueDelayEvent() {
        return new Queue(Constants.DELAY_EVENT_ROUTE_KEY, true); // 队列持久
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public Boolean getPublisherConfirm() {
        return publisherConfirm;
    }

    public void setPublisherConfirm(Boolean publisherConfirm) {
        this.publisherConfirm = publisherConfirm;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public String getExDefault() {
        return exDefault;
    }

    public void setExDefault(String exDefault) {
        this.exDefault = exDefault;
    }

    public String getExDelay() {
        return exDelay;
    }

    public void setExDelay(String exDelay) {
        this.exDelay = exDelay;
    }

    public Integer getDelayTimeDefault() {
        return delayTimeDefault;
    }

    public void setDelayTimeDefault(Integer delayTimeDefault) {
        this.delayTimeDefault = delayTimeDefault;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof MQPropConfiguration)) {
            return false;
        } else {
            MQPropConfiguration other = (MQPropConfiguration)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$host = this.getHost();
                Object other$host = other.getHost();
                if (this$host == null) {
                    if (other$host != null) {
                        return false;
                    }
                } else if (!this$host.equals(other$host)) {
                    return false;
                }

                if (this.getPort() != other.getPort()) {
                    return false;
                } else {
                    Object this$userName = this.getUserName();
                    Object other$userName = other.getUserName();
                    if (this$userName == null) {
                        if (other$userName != null) {
                            return false;
                        }
                    } else if (!this$userName.equals(other$userName)) {
                        return false;
                    }

                    Object this$password = this.getPassword();
                    Object other$password = other.getPassword();
                    if (this$password == null) {
                        if (other$password != null) {
                            return false;
                        }
                    } else if (!this$password.equals(other$password)) {
                        return false;
                    }

                    label93: {
                        Object this$virtualHost = this.getVirtualHost();
                        Object other$virtualHost = other.getVirtualHost();
                        if (this$virtualHost == null) {
                            if (other$virtualHost == null) {
                                break label93;
                            }
                        } else if (this$virtualHost.equals(other$virtualHost)) {
                            break label93;
                        }

                        return false;
                    }

                    label86: {
                        Object this$publisherConfirm = this.getPublisherConfirm();
                        Object other$publisherConfirm = other.getPublisherConfirm();
                        if (this$publisherConfirm == null) {
                            if (other$publisherConfirm == null) {
                                break label86;
                            }
                        } else if (this$publisherConfirm.equals(other$publisherConfirm)) {
                            break label86;
                        }

                        return false;
                    }

                    if (this.getCacheSize() != other.getCacheSize()) {
                        return false;
                    } else {
                        Object this$exDefault = this.getExDefault();
                        Object other$exDefault = other.getExDefault();
                        if (this$exDefault == null) {
                            if (other$exDefault != null) {
                                return false;
                            }
                        } else if (!this$exDefault.equals(other$exDefault)) {
                            return false;
                        }

                        label71: {
                            Object this$exDelay = this.getExDelay();
                            Object other$exDelay = other.getExDelay();
                            if (this$exDelay == null) {
                                if (other$exDelay == null) {
                                    break label71;
                                }
                            } else if (this$exDelay.equals(other$exDelay)) {
                                break label71;
                            }

                            return false;
                        }

                        Object this$delayTimeDefault = this.getDelayTimeDefault();
                        Object other$delayTimeDefault = other.getDelayTimeDefault();
                        if (this$delayTimeDefault == null) {
                            if (other$delayTimeDefault != null) {
                                return false;
                            }
                        } else if (!this$delayTimeDefault.equals(other$delayTimeDefault)) {
                            return false;
                        }

                        return true;
                    }
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MQPropConfiguration;
    }

    public int hashCode() {
        int result = 1;
        Object $host = this.getHost();
        result = result * 59 + ($host == null ? 43 : $host.hashCode());
        result = result * 59 + this.getPort();
        Object $userName = this.getUserName();
        result = result * 59 + ($userName == null ? 43 : $userName.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        Object $virtualHost = this.getVirtualHost();
        result = result * 59 + ($virtualHost == null ? 43 : $virtualHost.hashCode());
        Object $publisherConfirm = this.getPublisherConfirm();
        result = result * 59 + ($publisherConfirm == null ? 43 : $publisherConfirm.hashCode());
        result = result * 59 + this.getCacheSize();
        Object $exDefault = this.getExDefault();
        result = result * 59 + ($exDefault == null ? 43 : $exDefault.hashCode());
        Object $exDelay = this.getExDelay();
        result = result * 59 + ($exDelay == null ? 43 : $exDelay.hashCode());
        Object $delayTimeDefault = this.getDelayTimeDefault();
        result = result * 59 + ($delayTimeDefault == null ? 43 : $delayTimeDefault.hashCode());
        return result;
    }

    public String toString() {
        return "MQPropConfig(host=" + this.getHost() + ", port=" + this.getPort() + ", userName=" + this.getUserName() + ", password=" + this.getPassword() + ", virtualHost=" + this.getVirtualHost() + ", publisherConfirm=" + this.getPublisherConfirm() + ", cacheSize=" + this.getCacheSize() + ", exDefault=" + this.getExDefault() + ", exDelay=" + this.getExDelay() + ", delayTimeDefault=" + this.getDelayTimeDefault() + ")";
    }

}

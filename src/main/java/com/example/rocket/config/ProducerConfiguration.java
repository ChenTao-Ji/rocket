package com.example.rocket.config;

import com.example.rocket.property.ConfigProperties;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProducerConfiguration {

    @Autowired
    private ConfigProperties configProperties;

    @Bean
    public DefaultMQProducer getRocketMQProducer() throws Exception {
        //发送同一类消息的设置为同一个group，保证唯一,默认不需要设置，rocketmq会使用ip@pid(pid代表jvm名字)作为唯一标示
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer(configProperties.getProducer().getProducerGroup());
        defaultMQProducer.setNamesrvAddr(configProperties.getNameSrverAddr());
        defaultMQProducer.setVipChannelEnabled(false);
        //消息最大大小，默认4M
        defaultMQProducer.setMaxMessageSize(configProperties.getProducer().getMaxMessageSize());
        //消息发送超时时间，默认3秒
        defaultMQProducer.setSendMsgTimeout(configProperties.getProducer().getSendMsgTimeout());
        //如果发送消息失败，设置重试次数，默认为2次
        defaultMQProducer.setRetryTimesWhenSendFailed(configProperties.getProducer().getRetryTimesWhenSendFailed());
        try {
            defaultMQProducer.start();
        } catch (MQClientException e) {
            throw e;
        }
        return defaultMQProducer;
    }
}

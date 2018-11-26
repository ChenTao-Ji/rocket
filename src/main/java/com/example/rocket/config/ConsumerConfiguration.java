package com.example.rocket.config;

import com.example.rocket.property.ConfigProperties;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author chentao.ji
 */
@Component
public class ConsumerConfiguration {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ConsumeMsgListenerProcessor consumeMsgListenerProcessor;

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer() throws MQClientException {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer(configProperties.getConsumer().getConsumerGroup());
        defaultMQPushConsumer.setNamesrvAddr(configProperties.getNameSrverAddr());
        defaultMQPushConsumer.setConsumeThreadMax(configProperties.getConsumer().getConsumeThreadMax());
        defaultMQPushConsumer.setConsumeThreadMin(configProperties.getConsumer().getConsumeThreadMin());
        defaultMQPushConsumer.setMessageListener(consumeMsgListenerProcessor);
        defaultMQPushConsumer.setVipChannelEnabled(false);
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        /**
         * 设置一次消费消息的条数，默认为1条
         */
        defaultMQPushConsumer.setConsumeMessageBatchMaxSize(configProperties.getConsumer().getConsumeMessageBatchMaxSize());
        try {
            /**
             * 设置该消费者订阅的主题和tag，如果是订阅该主题下的所有tag，则tag使用*；如果需要指定订阅该主题下的某些tag，则使用||分割，例如tag1||tag2||tag3
             */
            String[] topicTagsArr = configProperties.getConsumer().getTopics().split(";");
            for (String topicTags : topicTagsArr) {
                String[] topicTag = topicTags.split("~");
                defaultMQPushConsumer.subscribe(topicTag[0], topicTag[1]);
            }
            defaultMQPushConsumer.start();
        } catch (MQClientException e) {
            throw e;
        }
        return defaultMQPushConsumer;
    }
}

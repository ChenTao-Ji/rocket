package com.example.rocket.config;

import com.example.rocket.property.ConfigProperties;
import com.example.rocket.service.ListenerService;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class ConsumeMsgListenerProcessor implements MessageListenerConcurrently {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ListenerService listenerService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if(CollectionUtils.isEmpty(msgs)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        for (MessageExt messageExt: msgs) {
            if(messageExt.getTopic().equals("chentao-topic")) {
                if (messageExt.getTags().equals("tag1")) {
                    System.out.println(msgs);
                    //listenerService
                }
            }
        }
        // 如果没有return success ，consumer会重新消费该消息，直到return success
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}

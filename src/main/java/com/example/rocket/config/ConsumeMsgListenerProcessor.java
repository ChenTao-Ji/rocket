package com.example.rocket.config;

import com.alibaba.fastjson.JSONObject;
import com.example.rocket.model.RocketProperties;
import com.example.rocket.property.ConfigProperties;
import com.example.rocket.service.*;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chentao.ji
 */
@Component
public class ConsumeMsgListenerProcessor implements MessageListenerConcurrently {

    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    DefaultMQProducer defaultMQProducer;

    @Autowired
    private ListenerService listenerService;

    @Autowired
    private AuoLoanService auoLoanService;

    @Autowired
    private AutoCmsService autoCmsService;

    @Autowired
    private AutoPlmsService autoPlmsService;

    @Autowired
    private AutoWechatService autoWechatService;

    @Autowired
    private WxocService wxocService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if(CollectionUtils.isEmpty(msgs)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        try {
            for (MessageExt messageExt: msgs) {
                //判断topic
                if(messageExt.getTopic().equals("chentao-topic")) {
                    //判断tags
                    if (messageExt.getTags().equals("tag1")) {
                        ConsumeConcurrentlyStatus consumeConcurrentlyStatus = listenerService.testMessage(messageExt);
                        if (ConsumeConcurrentlyStatus.CONSUME_SUCCESS.equals(consumeConcurrentlyStatus)) {
                            //TODO 从数据库取出回复消息内容
                            //topic + tag + is_valid
                            RocketProperties rocketProperties = new RocketProperties();
                            rocketProperties.setTopic("chentao-topic");
                            rocketProperties.setTags("tag1");
                            rocketProperties.setIsValid(1);
                            RocketProperties selectRocketProperties = listenerService.selectRocketProperties(rocketProperties);
                            String rocketDetal = selectRocketProperties.getRocketDetal();

                            Message message = new Message("chentao-topic", "tag1", rocketDetal.getBytes());
                            SendResult sendResult = defaultMQProducer.send(message);
                            System.out.println(sendResult.getSendStatus());
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                    }
                }
            }
        } catch (Exception e) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        // 如果没有return success ，consumer会重新消费该消息，直到return success
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}

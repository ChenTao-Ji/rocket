package com.example.rocket.service;

import com.example.rocket.model.RocketProperties;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author chentao.ji
 */
public interface ListenerService {

    ConsumeConcurrentlyStatus testMessage(MessageExt messageExt) throws Exception;

    int deleteByPrimaryKey(Long rocketId);

    int insert(RocketProperties record);

    int insertSelective(RocketProperties record);

    RocketProperties selectByPrimaryKey(Long rocketId);

    int updateByPrimaryKeySelective(RocketProperties record);

    int updateByPrimaryKey(RocketProperties record);

    RocketProperties selectRocketProperties(RocketProperties record);

}

package com.example.rocket.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.rocket.mappers.RocketPropertiesMapper;
import com.example.rocket.model.RocketProperties;
import com.example.rocket.service.ListenerService;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

/**
 * @author chentao.ji
 */
@Service
public class ListenerServiceImpl implements ListenerService {

    @Autowired
    private RocketPropertiesMapper rocketPropertiesMapper;

    /**
     * 定义消息内容字符编码
     */
    private static final String UTF8 = "UTF-8";

    @Override
    public ConsumeConcurrentlyStatus testMessage(MessageExt messageExt) throws Exception {
        Map<String, Object> map = JSON.parseObject(new String(messageExt.getBody(), UTF8), Map.class);
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = map.get(key);
            System.out.println("key:" + key + " value:" + value);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @Override
    public int deleteByPrimaryKey(Long rocketId) {
        return rocketPropertiesMapper.deleteByPrimaryKey(rocketId);
    }

    @Override
    public int insert(RocketProperties record) {
        return rocketPropertiesMapper.insert(record);
    }

    @Override
    public int insertSelective(RocketProperties record) {
        return rocketPropertiesMapper.insertSelective(record);
    }

    @Override
    public RocketProperties selectByPrimaryKey(Long rocketId) {
        return rocketPropertiesMapper.selectByPrimaryKey(rocketId);
    }

    @Override
    public int updateByPrimaryKeySelective(RocketProperties record) {
        return rocketPropertiesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RocketProperties record) {
        return rocketPropertiesMapper.updateByPrimaryKey(record);
    }

    @Override
    public RocketProperties selectRocketProperties(RocketProperties record) {
        return rocketPropertiesMapper.selectRocketProperties(record);
    }
}

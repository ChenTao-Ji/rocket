package com.example.rocket.mappers;

import com.example.rocket.model.RocketProperties;

public interface RocketPropertiesMapper {
    int deleteByPrimaryKey(Long rocketId);

    int insert(RocketProperties record);

    int insertSelective(RocketProperties record);

    RocketProperties selectByPrimaryKey(Long rocketId);

    RocketProperties selectRocketProperties(RocketProperties record);

    int updateByPrimaryKeySelective(RocketProperties record);

    int updateByPrimaryKey(RocketProperties record);
}
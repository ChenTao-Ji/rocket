package com.example.rocket.model;

import java.io.Serializable;

/**
 * rocket_properties
 * @author 
 */
public class RocketProperties implements Serializable {
    /**
     * 主键
     */
    private Long rocketId;

    /**
     * mq名字
     */
    private String rocketName;

    private String rocketDetal;

    private String topic;

    private String tags;

    /**
     * 1:有效,0:无效
     */
    private Integer isValid;

    private static final long serialVersionUID = 1L;

    public Long getRocketId() {
        return rocketId;
    }

    public void setRocketId(Long rocketId) {
        this.rocketId = rocketId;
    }

    public String getRocketName() {
        return rocketName;
    }

    public void setRocketName(String rocketName) {
        this.rocketName = rocketName;
    }

    public String getRocketDetal() {
        return rocketDetal;
    }

    public void setRocketDetal(String rocketDetal) {
        this.rocketDetal = rocketDetal;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }
}
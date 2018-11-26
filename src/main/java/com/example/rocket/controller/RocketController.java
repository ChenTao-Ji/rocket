package com.example.rocket.controller;

import com.example.rocket.model.RocketProperties;
import com.example.rocket.service.ListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author chentao.ji
 */
@RestController
@RequestMapping(value = "/rocket", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RocketController {

    @Autowired
    private ListenerService listenerService;

    @RequestMapping(value = "/delete/rocketId", method = RequestMethod.DELETE)
    public int deleteByPrimaryKey(@PathVariable Long rocketId) {
        return listenerService.deleteByPrimaryKey(rocketId);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public int insert(@RequestBody RocketProperties record) {
        return listenerService.insert(record);
    }

    @RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
    public int insertSelective(@RequestBody RocketProperties record) {
        return listenerService.insertSelective(record);
    }

    @RequestMapping(value = "/selectByPrimaryKey/rocketId", method = RequestMethod.GET)
    public RocketProperties selectByPrimaryKey(@PathVariable Long rocketId) {
        return listenerService.selectByPrimaryKey(rocketId);
    }

    @RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
    public int updateByPrimaryKeySelective(@RequestBody RocketProperties record) {
        return listenerService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping(value = "/updateByPrimaryKey", method = RequestMethod.POST)
    public int updateByPrimaryKey(@RequestBody RocketProperties record) {
        return listenerService.updateByPrimaryKey(record);
    }

    @RequestMapping(value = "/selectRocketProperties", method = RequestMethod.POST)
    public RocketProperties selectRocketProperties(@RequestBody RocketProperties record) {
        return listenerService.selectRocketProperties(record);
    }
}

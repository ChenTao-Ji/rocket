CREATE TABLE `rocket_properties` (
                                   `rocket_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `rocket_name` varchar(100) NOT NULL DEFAULT '' COMMENT 'mq名字',
                                   `rocket_detal` varchar(100) DEFAULT NULL,
                                   `topic` varchar(100) NOT NULL,
                                   `tags` varchar(100) NOT NULL,
                                   `is_valid` int(11) NOT NULL DEFAULT '1' COMMENT '1:有效,0:无效',
                                   PRIMARY KEY (`rocket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='rocketMQ配置信息'
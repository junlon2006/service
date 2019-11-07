
DROP TABLE IF EXISTS `tortoise_info`;
CREATE TABLE `tortoise_info` (
  `id` bigint(20) auto_increment NOT NULL COMMENT 'id',
  `name` varchar(100) NOT NULL COMMENT 'name',
  `birth_day` varchar(100) DEFAULT NULL COMMENT 'birthDay',
  `weight` DOUBLE DEFAULT NULL COMMENT 'weight',
  `type` varchar(100) DEFAULT NULL COMMENT 'type',
  `create_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nameType` (`name`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
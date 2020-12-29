DROP TABLE IF EXISTS `commandEntity`;
CREATE TABLE `commandEntity` (
    `id` int(11) NOT NULL auto_increment COMMENT '唯一id',
    `commandEntity` varchar(100) NOT NULL COMMENT '执行的命令',
    `code` char(3) default NULL COMMENT '结果状态码',
	`success` varchar(5) default NULL COMMENT '结果是否成功',
	`result` varchar(100) default NULL COMMENT '结果具体内容',
	`gmt_create` datetime  COMMENT '创建时间',
	`gmt_modified` datetime  COMMENT '更新时间',
	`deleted` int(1)  COMMENT '逻辑删除，0表示未删除，1表示删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
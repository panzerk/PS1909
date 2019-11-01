create table `analyze_result` (
    `id` int(10) not null auto_increment,
	`create_time` datetime not null,
	`analyze_type` varchar(30) not null,
	`analyze_text` text not null,
	`analyze_result` text not null,
	primary key (`id`)
) engine = innodb default charset = utf8;
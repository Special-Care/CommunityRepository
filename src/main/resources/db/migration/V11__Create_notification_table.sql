create table notification
(
	id bigint auto_increment,
	notifier bigint not null,
	receiver bigint not null,
	outer_id bigint not null,
	type int not null,
	gmt_create bigint not null,
	status int default 0 not null,
	constraint notification_pk
		primary key (id)
);

comment on column notification.id is '主键ID';

comment on column notification.notifier is '回复人';

comment on column notification.receiver is '接收通知者';

comment on column notification.outer_id is '通知的类型ID';

comment on column notification.type is '通知的类型';

comment on column notification.gmt_create is '创建通知的时间';

comment on column notification.status is '是否读取通知的状态';


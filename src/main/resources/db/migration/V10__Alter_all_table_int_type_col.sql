alter table user alter column id bigint auto_increment;

alter table question alter column id bigint auto_increment;

alter table question alter column creator bigint;

alter table comment alter column commentator bigint not null;
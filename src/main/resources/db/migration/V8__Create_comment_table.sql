create table comment
(
    id bigint auto_increment,
    parent bigint not null,
    type int not null,
    commentator int not null,
    gmt_create bigint not null,
    gmt_modified bigint not null,
    like_count bigint default 0,
    constraint comment_pk
        primary key (id)
);

comment on column comment.id is '主键';

comment on column comment.parent is '上级评论ID';

comment on column comment.type is '上级评论的类型';

comment on column comment.commentator is '评论者ID';

comment on column comment.gmt_create is '创建评论时间';

comment on column comment.gmt_modified is '评论修改时间';

comment on column comment.like_count is '点赞数';

##社区Community

##资料

[Spring boot引擎模板]
```text
thymeleaf
```

[github OAuth api - 授权登陆步骤](https://developer.github.com/apps/installing-github-apps/)

##工具
idea + git

##脚本
[h2 DataBase 创建用户sa - 密码 123]
```sql
CREATE USER IF NOT EXISTS sa PASSWORD '123';
ALTER USER sa admin true ;
```
[h2 DataBase sql]
```sql
create table USER(
    ID           INT auto_increment,
    ACCOUNT_ID   VARCHAR(100),
    NAME         VARCHAR(50),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_PK primary key (ID)
);

create table question
(
    id int auto_increment,
    title varchar(50),
    description text,
    gmt_create bigint,
    gmt_modify bigint,
    creator int,
    comment_count int default 0,
    view_count int default 0,
    like_count int default 0,
    tag varchar(25),
    constraint question_pk primary key (id)
);
```

##插件
```text
maven
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.10</version>
    <scope>provided</scope>
</dependency>

idea使用Lombok,需要在idea中Settings-->Plugins搜索Lombok插件安装方可使用
```
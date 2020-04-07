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
I.Flywaydb
Configuration can also be supplied directly via the command-line using JVM system properties:
    命令：mvn flyway:migrate
修复执行失败的flyway
    命令：mvn flyway:repair

II.自动生成get/set方法的Jar
maven
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.10</version>
    <scope>provided</scope>
</dependency>
注：idea使用Lombok,需要在idea中Settings-->Plugins搜索Lombok插件安装方可使用

III.pagehelper
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.13</version>
</dependency>
注：在Spring Boot中必须使用pagehelper-spring-boot-starter，不能使用pagehelper

IV.MyBatis Generator
1.The MBG Maven plugin includes one goal:
    命令：mybatis-generator:generate
2.The goal is not automatically executed by Maven. It can be executed in two ways.
  The goal can be executed from the command line with the command:
    命令：mvn mybatis-generator:generate
3.You can pass parameters to the goal with standard Maven command line properties. For example:
    命令：mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
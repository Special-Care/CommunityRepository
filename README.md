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

##总结
```text
    经过两个月的学习开发(虽然是跟随学习的)并将项目发布到服务器的过程，让我的开发经历也是有所提升。
当然最重要的是在该项目中我学习到许多的思维逻辑、程序的严谨性和应对错误的处理方式。在项目中我也学习
到很多的知识点，也包括其他一些插件和工具的使用。
    之前我对Spring Boot框架只是初步学习的阶段，经过这段时间对该项目的学习开发，让我也掌握了一些
Spring Boot的用法。相对Spring Framework框架，该框架简化了配置文件的使用，如XML文件，统一使用
application.properties配置。同时在该项目中有三个插件，让我觉得可以让我以后在开发过程中可以减少
代码的书写和提高工作效率，这三个插件分别是Lombok、Flyway、MyBatis Generator.
                                                     个人最喜欢的一句话：I am here with you !
                                                                          署名：SpecialCare
```
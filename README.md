##社区Community

##资料
[Spring boot模板](spring-boot-starter-thymeleaf)

[github OAuth api - 授权登陆](https://developer.github.com/apps/installing-github-apps/)

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
```
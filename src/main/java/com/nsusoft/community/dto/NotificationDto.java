package com.nsusoft.community.dto;

import lombok.Data;

@Data
public class NotificationDto {
    private Long id;
    //通知的时间
    private Long gmtCreate;
    //通知的状态(已读/未读)
    private int status;
    //发送通知者ID
    private Long notifier;
    //发送通知者名字
    private String notificationName;
    //问题ID
    private Long outerId;
    //问题名
    private String outerTitle;
    //通知的类型
    private Integer type;
    //通知的类型
    private String typeName;
}

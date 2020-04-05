package com.nsusoft.community.service;

import com.github.pagehelper.PageHelper;
import com.nsusoft.community.dto.NotificationDto;
import com.nsusoft.community.entity.Notification;
import com.nsusoft.community.entity.NotificationExample;
import com.nsusoft.community.entity.User;
import com.nsusoft.community.enums.NotificationStatusEnum;
import com.nsusoft.community.enums.NotificationTypeEnum;
import com.nsusoft.community.exception.MyException;
import com.nsusoft.community.exception.MyHttpStatus;
import com.nsusoft.community.mapper.NotificationMapper;
import com.nsusoft.community.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    //查询该用户未读通知列表
    public List<NotificationDto> queryUnreadNotification(Long id, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.setOrderByClause("gmt_create desc");
        notificationExample.createCriteria()
                .andReceiverEqualTo(id);
        List<Notification> notifications = notificationMapper.selectByExample(notificationExample);

        List<NotificationDto> notificationDtoList = new ArrayList<>();

        //通知为空
        if (notifications.isEmpty())
            return notificationDtoList;

        for (Notification notification : notifications) {
            NotificationDto notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notification, notificationDto);
            notificationDto.setTypeName(NotificationTypeEnum.getTypeName(notification.getType()));
            notificationDtoList.add(notificationDto);
        }
        return notificationDtoList;
    }

    //查询该用户未读通知数量
    public Long queryUnreadCount(Long id) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(id)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    //读取 未读的通知
    public NotificationDto obtainNotification(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null)
            throw new MyException(MyHttpStatus.FOUND_NOTIFICATION);

        if (notification.getReceiver() ==  user.getId())
            throw new MyException(MyHttpStatus.READ_NOTIFICATION_FAIL);

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDto notificationDto = new NotificationDto();
        BeanUtils.copyProperties(notification, notificationDto);
        notificationDto.setTypeName(NotificationTypeEnum.getTypeName(notification.getType()));
        return notificationDto;
    }
}

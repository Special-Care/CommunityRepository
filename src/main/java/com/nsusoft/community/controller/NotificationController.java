package com.nsusoft.community.controller;

import com.nsusoft.community.dto.NotificationDto;
import com.nsusoft.community.entity.User;
import com.nsusoft.community.enums.NotificationTypeEnum;
import com.nsusoft.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String replies(HttpServletRequest request, @PathVariable("id") Long id) {
        //判断用户是否登陆
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
            return "redirect:/";

        NotificationDto notificationDto = notificationService.obtainNotification(id, user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDto.getType()
                ||NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDto.getType())
            return "redirect:/question/" + notificationDto.getOuterId();

        return "redirect:/";
    }
}

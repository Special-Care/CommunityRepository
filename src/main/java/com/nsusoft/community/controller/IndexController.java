package com.nsusoft.community.controller;

import com.nsusoft.community.entity.User;
import com.nsusoft.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper mapper;

    @RequestMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                User user = mapper.queryByToken(token);
                if (user != null)
                    request.getSession().setAttribute("user", user);
                break;
            }
        }
        return "index";
    }
}

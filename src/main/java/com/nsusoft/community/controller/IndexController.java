package com.nsusoft.community.controller;

import com.nsusoft.community.entity.Question;
import com.nsusoft.community.entity.User;
import com.nsusoft.community.mapper.QuestionMapper;
import com.nsusoft.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @RequestMapping("/")
    public String index(HttpServletRequest request, ModelMap modelMap) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie:cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.queryByToken(token);
                    if (user != null)
                        request.getSession().setAttribute("user", user);
                    break;
                }
            }
        }

        List<Question> questions = questionMapper.queryAllQuestion();
        modelMap.addAttribute("questions", questions);
        return "index";
    }
}

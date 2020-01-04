package com.nsusoft.community.controller;

import com.github.pagehelper.PageInfo;
import com.nsusoft.community.entity.Question;
import com.nsusoft.community.entity.User;
import com.nsusoft.community.mapper.QuestionMapper;
import com.nsusoft.community.mapper.UserMapper;
import com.nsusoft.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService service;

    @RequestMapping("/")
    public String index(@RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "10") Integer size,
                        HttpServletRequest request, ModelMap modelMap) {

        List<Question> questions = service.queryAllQuestion(page, size);
        PageInfo pageInfo = new PageInfo(questions);
        modelMap.addAttribute("pageInfo", pageInfo);
        return "index";
    }
}

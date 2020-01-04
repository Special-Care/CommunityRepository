package com.nsusoft.community.controller;

import com.github.pagehelper.PageInfo;
import com.nsusoft.community.entity.Question;
import com.nsusoft.community.entity.User;
import com.nsusoft.community.mapper.UserMapper;
import com.nsusoft.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService service;

    @RequestMapping("/profile/{action}")
    public String profile(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                          @PathVariable(name = "action")String action, ModelMap modelMap,
                          HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null)
            return "redirect:/";

        if ("questions".equals(action)) {
            modelMap.addAttribute("section", "questions");
            modelMap.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            modelMap.addAttribute("section", "replies");
            modelMap.addAttribute("sectionName", "最新回复");
        }

        List<Question> questions = service.queryCreatorByUserId(user.getId(), page, size);
        PageInfo pageInfo = new PageInfo(questions);
        modelMap.addAttribute("pageInfo", pageInfo);

        return "profile";
    }
}

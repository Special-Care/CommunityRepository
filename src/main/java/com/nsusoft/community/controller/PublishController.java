package com.nsusoft.community.controller;

import com.nsusoft.community.entity.Question;
import com.nsusoft.community.entity.User;
import com.nsusoft.community.mapper.QuestionMapper;
import com.nsusoft.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/publish")
    public String publish() {
        return "publish";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request, ModelMap modelMap) {
        modelMap.addAttribute("title", title);
        modelMap.addAttribute("description", description);
        modelMap.addAttribute("tag", tag);

        if (title == null || title == "") {
            modelMap.addAttribute("error", "It's title is null");
            return "publish";
        }
        if (description == null || description == "") {
            modelMap.addAttribute("error", "It's description is null");
            return "publish";
        }
        if (tag == null || tag == "") {
            modelMap.addAttribute("error", "It's tag is null");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            modelMap.addAttribute("error", "Not logged in");
            return "publish";
        }


        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModify(question.getGmtCreate());
        question.setCreator(user.getId());
        question.setTag(tag);
        questionMapper.create(question);
        return "redirect:/";
    }
}

package com.nsusoft.community.controller;

import com.nsusoft.community.entity.Question;
import com.nsusoft.community.entity.User;
import com.nsusoft.community.mapper.QuestionMapper;
import com.nsusoft.community.mapper.UserMapper;
import com.nsusoft.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService service;

    @RequestMapping("/publish")
    public String publish() {
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id, ModelMap modelMap) {
        if (id != null) {
            Question question = service.queryQustionById(id);
            modelMap.addAttribute("id", question.getId());
            modelMap.addAttribute("title", question.getTitle());
            modelMap.addAttribute("description", question.getDescription());
            modelMap.addAttribute("tag", question.getTag());
        }
        return "publish";
    }


    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String doPublish(@RequestParam(value = "id", required = false) Integer id,
                            @RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            HttpServletRequest request, ModelMap modelMap) {

        modelMap.addAttribute("id", id);
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
        if (user.equals(null)) {
            modelMap.addAttribute("error", "Not logged in");
            return "publish";
        }


        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setCreator(user.getId());
        question.setTag(tag);
        service.createOrUpdate(question);
        //questionMapper.create(question);
        return "redirect:/";
    }
}

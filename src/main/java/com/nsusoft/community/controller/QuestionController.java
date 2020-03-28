package com.nsusoft.community.controller;

import com.nsusoft.community.dto.QuestionDto;
import com.nsusoft.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService service;

    @RequestMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id, ModelMap modelMap) {
        QuestionDto question = service.queryQustionById(id);
        //阅读量
        service.reading(id);
        modelMap.addAttribute("question", question);
        return "question";
    }
}

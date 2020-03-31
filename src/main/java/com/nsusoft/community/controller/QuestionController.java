package com.nsusoft.community.controller;

import com.nsusoft.community.dto.CommentExtraDto;
import com.nsusoft.community.dto.QuestionDto;
import com.nsusoft.community.enums.CommentTypeEnum;
import com.nsusoft.community.service.CommentService;
import com.nsusoft.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService service;

    @Autowired
    private CommentService commentService;

    @RequestMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, ModelMap modelMap) {
        QuestionDto question = service.queryQustionById(id);
        //阅读量
        service.reading(id);

        List<CommentExtraDto> commentExtraDtos = commentService.queryCommentsByParentId(id, CommentTypeEnum.QUESTION);

        modelMap.addAttribute("question", question);
        modelMap.addAttribute("commentList", commentExtraDtos);
        return "question";
    }
}

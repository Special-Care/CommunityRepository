package com.nsusoft.community.controller;

import com.nsusoft.community.dto.QuestionDto;
import com.nsusoft.community.entity.Question;
import com.nsusoft.community.entity.User;
import com.nsusoft.community.service.QuestionService;
import com.nsusoft.community.utils.TagsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService service;

    @RequestMapping("/publish")
    public String publish(ModelMap modelMap) {
        modelMap.addAttribute("tagsDtoList", TagsUtil.getListTags());
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id, ModelMap modelMap) {
        QuestionDto question = service.queryQustionById(id);
        modelMap.addAttribute("id", question.getId());
        modelMap.addAttribute("title", question.getTitle());
        modelMap.addAttribute("description", question.getDescription());
        modelMap.addAttribute("tag", question.getTag());
        modelMap.addAttribute("tagsDtoList", TagsUtil.getListTags());
        return "publish";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String doPublish(@RequestParam(value = "id", required = false) Long id,
                            @RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            HttpServletRequest request, ModelMap modelMap) {
        //modelMap.addAttribute("id", id);
        modelMap.addAttribute("title", title);
        modelMap.addAttribute("description", description);
        modelMap.addAttribute("tag", tag);
        modelMap.addAttribute("tagsDtoList", TagsUtil.getListTags());

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            modelMap.addAttribute("error", "Not logged in");
            return "publish";
        }

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

        String inValid = TagsUtil.filterInValid(tag);
        if (StringUtils.isNotBlank(inValid)) {
            modelMap.addAttribute("error", "It's tag is error ==>" + inValid);
            return "publish";
        }

        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setCreator(user.getId());
        question.setTag(tag);
        service.createOrUpdate(question);
        return "redirect:/";
    }
}

package com.nsusoft.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @RequestMapping("/index")
    public String index(@RequestParam(value = "name") String name, ModelMap modelMap) {
        modelMap.addAttribute("name", name);
        return "index";
    }
}

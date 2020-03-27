package com.nsusoft.community.advice;

import com.nsusoft.community.exception.MyException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleControllerException(HttpServletRequest request, Throwable ex, Model model) {
        if (ex instanceof MyException) {
            model.addAttribute("message", ex.getMessage());
        } else {
            model.addAttribute("message", "服务器未捕获的异常");
        }
        return new ModelAndView("error");
    }
}

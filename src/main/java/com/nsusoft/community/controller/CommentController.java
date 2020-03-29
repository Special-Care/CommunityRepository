package com.nsusoft.community.controller;

import com.nsusoft.community.dto.CommentDto;
import com.nsusoft.community.dto.ResultDto;
import com.nsusoft.community.entity.Comment;
import com.nsusoft.community.entity.User;
import com.nsusoft.community.exception.MyHttpStatus;
import com.nsusoft.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public ResultDto comment(@RequestBody CommentDto commentDto, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null)
            return ResultDto.result(MyHttpStatus.NO_LOGIN);

        Comment comment = new Comment();
        comment.setParent(commentDto.getParentID());
        comment.setType(commentDto.getType());
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setContent(commentDto.getContent());

        commentService.createComment(comment);


        return ResultDto.result(MyHttpStatus.COMMENT_SUCCESS);
    }
}

package com.nsusoft.community.controller;

import com.nsusoft.community.dto.CommentDto;
import com.nsusoft.community.dto.CommentExtraDto;
import com.nsusoft.community.dto.ResultDto;
import com.nsusoft.community.entity.Comment;
import com.nsusoft.community.entity.User;
import com.nsusoft.community.enums.CommentTypeEnum;
import com.nsusoft.community.exception.MyHttpStatus;
import com.nsusoft.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    //创建一级评论
    @PostMapping("/comment")
    @ResponseBody
    public ResultDto createTopComment(@RequestBody CommentDto commentDto, HttpServletRequest request) {
        User user = getUser(request);

        if (user == null)
            return ResultDto.result(MyHttpStatus.NO_LOGIN);

        if (commentDto == null || StringUtils.isBlank(commentDto.getContent()))
            return ResultDto.result(MyHttpStatus.CONTENT_BLANK);

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

    //查找二级评论
    @GetMapping("/comment/{id}")
    @ResponseBody
    public ResultDto<List> querySecondComment(@PathVariable Long id) {
        List<CommentExtraDto> commentExtraDtos = commentService.queryCommentsByParentId(id, CommentTypeEnum.COMMENT);
        return ResultDto.result(MyHttpStatus.QUERY_COMMENTS, commentExtraDtos);
    }

    //获取session中的User
    private User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }
}

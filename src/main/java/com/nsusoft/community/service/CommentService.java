package com.nsusoft.community.service;

import com.nsusoft.community.dto.CommentDto;
import com.nsusoft.community.dto.CommentExtraDto;
import com.nsusoft.community.entity.Comment;
import com.nsusoft.community.entity.CommentExample;
import com.nsusoft.community.entity.Question;
import com.nsusoft.community.entity.User;
import com.nsusoft.community.enums.CommentTypeEnum;
import com.nsusoft.community.exception.MyException;
import com.nsusoft.community.exception.MyHttpStatus;
import com.nsusoft.community.mapper.CommentMapper;
import com.nsusoft.community.mapper.QuestionExtraMapper;
import com.nsusoft.community.mapper.QuestionMapper;
import com.nsusoft.community.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtraMapper questionExtraMapper;

    @Autowired
    private UserMapper userMapper;

    public void createComment(Comment comment) {
        if(comment.getParent() == null || comment.getParent() == 0)
            throw new MyException(MyHttpStatus.TARGET_PARENT_NOT_FOUND);

        if (comment.getType() == null || !CommentTypeEnum.isExit(comment.getType()))
            throw new MyException(MyHttpStatus.TYPE_PARENT_WRONG);

        if (comment.getType() == CommentTypeEnum.QUESTION.getType()) {
            //问题回复
            Question question = questionMapper.selectByPrimaryKey(comment.getParent());

            if (question == null)
                throw new MyException(MyHttpStatus.QUESTION_NOT_FOUND);

            commentMapper.insert(comment);

            question.setCommentCount(1);
            questionExtraMapper.commentCount(question);
        } else {
            //评论回复
            Comment selectByPrimaryKey = commentMapper.selectByPrimaryKey(comment.getParent());

            if (selectByPrimaryKey == null)
                throw new MyException(MyHttpStatus.COMMENT_PARENT_WRONG);

            commentMapper.insert(comment);
        }
    }

    public List<CommentExtraDto> queryAllComment(Long id) {
        List<CommentExtraDto> commentExtraDtos = new ArrayList<>();

        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentEqualTo(id)
                .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if (comments.isEmpty())
            return commentExtraDtos;

        for (Comment comment : comments) {
            CommentExtraDto commentExtraDto = new CommentExtraDto();
            BeanUtils.copyProperties(comment, commentExtraDto);
            User user = userMapper.selectByPrimaryKey(comment.getCommentator());
            commentExtraDto.setUser(user);

            commentExtraDtos.add(commentExtraDto);
        }
        return commentExtraDtos;
    }
}

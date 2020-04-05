package com.nsusoft.community.service;

import com.nsusoft.community.dto.CommentExtraDto;
import com.nsusoft.community.entity.*;
import com.nsusoft.community.enums.CommentTypeEnum;
import com.nsusoft.community.enums.NotificationStatusEnum;
import com.nsusoft.community.enums.NotificationTypeEnum;
import com.nsusoft.community.exception.MyException;
import com.nsusoft.community.exception.MyHttpStatus;
import com.nsusoft.community.mapper.*;
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
    private UserMapper userMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtraMapper questionExtraMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    public void createComment(Comment comment, User notificator) {
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

            //问题评论数加一
            question.setCommentCount(1);
            questionExtraMapper.commentCount(question);

            //问题回复的通知
            createNotification(comment, notificator.getName(), question.getCreator(), question.getId(), question.getTitle(), NotificationTypeEnum.REPLY_QUESTION);
        } else {
            //评论回复
            Comment parentComment = commentMapper.selectByPrimaryKey(comment.getParent());

            if (parentComment == null)
                throw new MyException(MyHttpStatus.COMMENT_PARENT_WRONG);

            //获取问题是否存在，并获取问题Title
            Question question = questionMapper.selectByPrimaryKey(parentComment.getParent());
            if (question == null)
                throw new MyException(MyHttpStatus.QUESTION_NOT_FOUND);

            commentMapper.insert(comment);
            //评论回复的通知
            createNotification(comment, notificator.getName(), parentComment.getCommentator(), question.getId(), question.getTitle(), NotificationTypeEnum.REPLY_COMMENT);
        }
    }

    public List<CommentExtraDto> queryCommentsByParentId(Long id, CommentTypeEnum type) {
        List<CommentExtraDto> commentExtraDtos = new ArrayList<>();

        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("gmt_create desc");
        commentExample.createCriteria()
                .andParentEqualTo(id)
                .andTypeEqualTo(type.getType());
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

    //评论通知
    private void createNotification(Comment comment, String userName, Long receiver, Long questionId, String title, NotificationTypeEnum typeEnum) {
        Notification notification = new Notification();
        notification.setNotifier(comment.getCommentator());
        notification.setReceiver(receiver);
        notification.setOuterId(questionId);
        notification.setType(typeEnum.getType());
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setNotificationName(userName);
        notification.setOuterTitle(title);
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notificationMapper.insert(notification);
    }
}

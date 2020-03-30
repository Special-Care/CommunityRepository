package com.nsusoft.community.dto;

import com.nsusoft.community.entity.User;
import lombok.Data;

@Data
public class CommentExtraDto {
    private Long id;
    private Long parent;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
}

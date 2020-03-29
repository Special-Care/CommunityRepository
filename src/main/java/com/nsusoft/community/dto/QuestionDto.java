package com.nsusoft.community.dto;

import com.nsusoft.community.entity.User;
import lombok.Data;

@Data
public class QuestionDto {
    private Long id;
    private String title;
    private Long gmtCreate;
    private Long gmtModify;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private String description;
    private User user;
}

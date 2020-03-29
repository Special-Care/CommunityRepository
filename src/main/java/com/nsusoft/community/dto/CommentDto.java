package com.nsusoft.community.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long parentID;
    private String content;
    private int type;
}

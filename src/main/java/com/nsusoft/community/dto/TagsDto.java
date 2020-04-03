package com.nsusoft.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class TagsDto {
    private String topTag;
    private List<String> secondTags;
}

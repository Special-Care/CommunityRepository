package com.nsusoft.community.mapper;

import com.nsusoft.community.entity.Question;

public interface QuestionExtraMapper {
    int reading(Question question);
    int commentCount(Question question);
}
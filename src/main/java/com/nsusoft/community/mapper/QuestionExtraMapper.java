package com.nsusoft.community.mapper;

import com.nsusoft.community.entity.Question;

import java.util.List;

public interface QuestionExtraMapper {
    int reading(Question question);
    int commentCount(Question question);
    List<Question> selectRelated(Question question);
    List<Question> selectSearch(String search);
}
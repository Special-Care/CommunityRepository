package com.nsusoft.community.service;

import com.github.pagehelper.PageHelper;
import com.nsusoft.community.entity.Question;
import com.nsusoft.community.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper mapper;

    public List<Question> queryAllQuestion(int page, int size) {
        PageHelper.startPage(page, size);
        return mapper.queryAllQuestion();
    }
}

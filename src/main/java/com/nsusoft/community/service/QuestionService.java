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

    public List<Question> queryCreatorByUserId(Integer id, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return mapper.queryCreatorByUserId(id);
    }

    public Question queryQustionById(Integer id) {
        return mapper.queryQustionById(id);
    }

    public void createOrUpdate(Question question) {
        if (question.getId().equals(null)) {
            //id为空，则question是新的问题
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            mapper.create(question);
        } else {
            //id不为空，则question是编辑过后的问题
            question.setGmtModify(question.getGmtCreate());
            mapper.update(question);
        }
    }
}

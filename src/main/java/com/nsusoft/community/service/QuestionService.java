package com.nsusoft.community.service;

import com.github.pagehelper.PageHelper;
import com.nsusoft.community.dto.QuestionDto;
import com.nsusoft.community.entity.Question;
import com.nsusoft.community.entity.QuestionExample;
import com.nsusoft.community.entity.User;
import com.nsusoft.community.mapper.QuestionMapper;
import com.nsusoft.community.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDto> queryAllQuestion(int page, int size) {
        PageHelper.startPage(page, size);

        List<QuestionDto> qusetionDtoList = new ArrayList<>();

        List<Question> questions = questionMapper.selectByExampleWithBLOBs(new QuestionExample());
        for (Question question : questions) {
            QuestionDto qusetionDto = new QuestionDto();
            BeanUtils.copyProperties(question, qusetionDto);

            User user = userMapper.selectByPrimaryKey(question.getCreator());

            qusetionDto.setUser(user);
            qusetionDtoList.add(qusetionDto);
        }
        return qusetionDtoList;
    }

    public List<QuestionDto> queryCreatorByUserId(Integer id, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<QuestionDto> qusetionDtoList = new ArrayList<>();

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        List<Question> questions = questionMapper.selectByExampleWithBLOBs(questionExample);

        for (Question question : questions) {
            QuestionDto qusetionDto = new QuestionDto();
            BeanUtils.copyProperties(question, qusetionDto);

            User user = userMapper.selectByPrimaryKey(id);

            qusetionDto.setUser(user);
            qusetionDtoList.add(qusetionDto);
        }

        return qusetionDtoList;
    }

    public QuestionDto queryQustionById(Integer id) {
        QuestionDto qusetionDto = new QuestionDto();

        Question question = questionMapper.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(question, qusetionDto);
        qusetionDto.setUser(user);

        return qusetionDto;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //id为空，则question是新的问题
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            questionMapper.insert(question);
        } else {
            //id不为空，则question是编辑过后的问题
            Question updateQuestion = new Question();
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            updateQuestion.setGmtModify(System.currentTimeMillis());

            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(question.getId());

            questionMapper.updateByExampleSelective(updateQuestion, questionExample);
        }
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

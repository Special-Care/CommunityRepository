package com.nsusoft.community.service;

import com.github.pagehelper.PageHelper;
import com.nsusoft.community.dto.QuestionDto;
import com.nsusoft.community.entity.Question;
import com.nsusoft.community.entity.QuestionExample;
import com.nsusoft.community.entity.User;
import com.nsusoft.community.exception.MyException;
import com.nsusoft.community.exception.MyHttpStatus;
import com.nsusoft.community.mapper.QuestionExtraMapper;
import com.nsusoft.community.mapper.QuestionMapper;
import com.nsusoft.community.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtraMapper questionExtraMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDto> queryAllQuestion(int page, int size, String search) {
        PageHelper.startPage(page, size);

        if (StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search, " ");
            String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        }

        List<Question> questions = questionExtraMapper.selectSearch(search);

        return getList(questions);
    }

    public List<QuestionDto> queryCreatorByUserId(Long id, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<QuestionDto> qusetionDtoList = new ArrayList<>();

        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
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

    public QuestionDto queryQustionById(Long id) {
        QuestionDto qusetionDto = new QuestionDto();

        Question question = questionMapper.selectByPrimaryKey(id);

        if (question == null)
            throw new MyException(MyHttpStatus.QUESTION_NOT_FOUND);

        User user = userMapper.selectByPrimaryKey(question.getCreator());
        BeanUtils.copyProperties(question, qusetionDto);
        qusetionDto.setUser(user);

        return qusetionDto;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //id为空，则question是新的问题
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            question.setCommentCount(0);
            question.setViewCount(0);
            question.setLikeCount(0);
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

            if (questionMapper.updateByExampleSelective(updateQuestion, questionExample) != 1)
                throw new MyException(MyHttpStatus.QUESTION_NOT_FOUND);
        }
    }

    public void reading(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);

        questionExtraMapper.reading(question);
    }

    public List<QuestionDto> queryRelatedQuestion(QuestionDto question) {
        if (StringUtils.isBlank(question.getTag()))
            return new ArrayList<>();

        String[] tags = StringUtils.split(question.getTag(), ",");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question questionTag = new Question();
        questionTag.setId(question.getId());
        questionTag.setTag(regexpTag);


        List<Question> questions = questionExtraMapper.selectRelated(questionTag);


        return getList(questions);
    }

    private List<QuestionDto> getList(List<Question> questions) {
        List<QuestionDto> qusetionDtoList = new ArrayList<>();
        for (Question question : questions) {
            QuestionDto qusetionDto = new QuestionDto();
            BeanUtils.copyProperties(question, qusetionDto);

            User user = userMapper.selectByPrimaryKey(question.getCreator());

            qusetionDto.setUser(user);
            qusetionDtoList.add(qusetionDto);
        }

        return qusetionDtoList;
    }
}

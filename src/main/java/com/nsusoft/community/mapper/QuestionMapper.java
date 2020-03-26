package com.nsusoft.community.mapper;

import com.nsusoft.community.entity.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title, description, gmt_create, gmt_modify, creator, tag) " +
            "values(#{title}, #{description}, #{gmtCreate}, #{gmtModify}, #{creator}, #{tag})")
    void create(Question question);

    @Select("select * from question")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModify", column = "gmt_modify"),
            @Result(property = "creator", column = "creator"),
            @Result(property = "commentCount", column = "comment_count"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "tag", column = "tag"),
            @Result(property = "user", column = "creator",
                    one = @One(select = "com.nsusoft.community.mapper.UserMapper.queryByUserId"))
    })
    List<Question> queryAllQuestion();

    @Select("select * from question where creator = ${id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModify", column = "gmt_modify"),
            @Result(property = "creator", column = "creator"),
            @Result(property = "commentCount", column = "comment_count"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "tag", column = "tag"),
            @Result(property = "user", column = "creator",
                    one = @One(select = "com.nsusoft.community.mapper.UserMapper.queryByUserId"))
    })
    List<Question> queryCreatorByUserId(Integer id);

    @Select("select * from question where id = ${id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModify", column = "gmt_modify"),
            @Result(property = "creator", column = "creator"),
            @Result(property = "commentCount", column = "comment_count"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "likeCount", column = "like_count"),
            @Result(property = "tag", column = "tag"),
            @Result(property = "user", column = "creator",
                    one = @One(select = "com.nsusoft.community.mapper.UserMapper.queryByUserId"))
    })
    Question queryQustionById(Integer id);

    @Update("update question set title = #{title}, description = #{description}, gmt_modify = #{gmtModify}, tag = #{tag} where id = #{id}")
    void update(Question question);
}

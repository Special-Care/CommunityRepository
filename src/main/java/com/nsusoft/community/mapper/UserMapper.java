package com.nsusoft.community.mapper;

import com.nsusoft.community.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id, name, token, gmt_create, gmt_modified, photo_url) values(#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{photoUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User queryByToken(String token);

    @Select("select * from user where id = #{id}")
    User queryByUserId(String id);

    @Select("select * from user where account_id = #{accountId}")
    User queryByAccountId(String accountId);

    @Update("update user set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, photo_url = #{photoUrl} where id = #{id}")
    void update(User user);
}

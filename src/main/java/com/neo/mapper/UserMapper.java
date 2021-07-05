package com.neo.mapper;

import com.neo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    List<User> getAll();

    User getOne(Long id);

    User selectById(@Param("id") Integer id);
    User selectById2();
    User selectByIdOrName(@Param("id") Integer id, @Param("name") String name);

    void insert(User user);

    void update(User user);

    void delete(Long id);

}

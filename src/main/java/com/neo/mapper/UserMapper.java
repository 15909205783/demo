package com.neo.mapper;

import com.neo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    List<User> getAll();

    User getOne(Long id);

    User selectById(Integer id);

    void insert(User user);

    void update(User user);

    void delete(Long id);

}

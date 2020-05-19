package com.neo.mapper;

import com.neo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMapper {
    List<User> getAll();

    User getOne(Long id);

    void insert(User user);

    void update(User user);

    void delete(Long id);

}

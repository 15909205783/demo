package com.neo.service.impl;

import com.neo.mapper.UserMapper;
import com.neo.model.User;
import com.neo.service.UserMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserMapperServiceImpl implements UserMapperService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }
}

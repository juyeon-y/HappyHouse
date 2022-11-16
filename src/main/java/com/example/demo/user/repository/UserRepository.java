package com.example.demo.user.repository;

import com.example.demo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {

    User findByEmail(String email);

    void save(User user);
}

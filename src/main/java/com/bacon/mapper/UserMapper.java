package com.bacon.mapper;

import com.bacon.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    User findById(Long id);
    User findByUserId(String userId);
    List<User> findAll();
    List<User> searchByKeyword(String keyword);
    void insert(User user);
    void update(User user);
    void delete(Long id);
}
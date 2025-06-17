package com.bacon.service;

import com.bacon.entity.User;
import com.bacon.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findById(Long id) {
        return userMapper.findById(id);
    }

    public User findByUserId(String userId) {
        return userMapper.findByUserId(userId);
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public List<User> searchUsers(String keyword) {
        return userMapper.searchByKeyword(keyword);
    }

    public User register(String userId, String username, String password) {
        // Check if user already exists
        if (userMapper.findByUserId(userId) != null) {
            throw new RuntimeException("User ID already exists");
        }

        // Create new user
        User user = new User(userId, username, hashPassword(password));
        userMapper.insert(user);
        return user;
    }

    public User authenticate(String userId, String password) {
        User user = userMapper.findByUserId(userId);

        if (user != null && user.getPasswordHash().equals(hashPassword(password))) {
            return user;
        }
        return null;
    }

    public void updateProfile(Long id, String username, String bio, String color) {
        User user = userMapper.findById(id);
        if (user != null) {
            user.setUsername(username);
            user.setBio(bio);
            user.setColor(color);
            userMapper.update(user);
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
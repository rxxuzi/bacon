package com.bacon.mapper;

import com.bacon.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PostMapper {
    Post findById(Long id);
    List<Post> findByUserId(Long userId);
    List<Post> findPublicPosts();
    List<Post> findTimeline();
    List<Post> searchByKeyword(String keyword);
    void insert(Post post);
    void update(Post post);
    void delete(Long id);
    void incrementReplyCount(Long id);
}
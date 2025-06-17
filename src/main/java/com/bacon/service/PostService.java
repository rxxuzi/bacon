package com.bacon.service;

import com.bacon.entity.Post;
import com.bacon.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    public Post findById(Long id) {
        return postMapper.findById(id);
    }

    public List<Post> findByUserId(Long userId) {
        return postMapper.findByUserId(userId);
    }

    public List<Post> getTimeline() {
        return postMapper.findTimeline();
    }

    public List<Post> searchPosts(String keyword) {
        return postMapper.searchByKeyword(keyword);
    }

    public Post createPost(Long userId, String content, String visibility) {
        Post post = new Post(userId, content, visibility);
        postMapper.insert(post);
        return post;
    }

    public void updatePost(Long id, String content, String visibility) {
        Post post = postMapper.findById(id);
        if (post != null) {
            post.setContent(content);
            post.setVisibility(visibility);
            postMapper.update(post);
        }
    }

    public void deletePost(Long id) {
        postMapper.delete(id);
    }

    public void addReply(Long id) {
        postMapper.incrementReplyCount(id);
    }
}
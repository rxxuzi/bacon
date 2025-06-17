package com.bacon.service;

import com.bacon.entity.Bookmark;
import com.bacon.mapper.BookmarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkMapper bookmarkMapper;

    public List<Bookmark> getUserBookmarks(Long userId) {
        return bookmarkMapper.findByUserId(userId);
    }

    public boolean isBookmarked(Long userId, Long postId) {
        return bookmarkMapper.findByUserAndPost(userId, postId) != null;
    }

    public Bookmark addBookmark(Long userId, Long postId) {
        // Check if already bookmarked
        if (isBookmarked(userId, postId)) {
            return null;
        }

        Bookmark bookmark = new Bookmark(userId, postId);
        bookmarkMapper.insert(bookmark);
        return bookmark;
    }

    public void removeBookmark(Long userId, Long postId) {
        bookmarkMapper.deleteByUserAndPost(userId, postId);
    }
}
package com.bacon.mapper;

import com.bacon.entity.Bookmark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BookmarkMapper {
    Bookmark findById(Long id);
    Bookmark findByUserAndPost(@Param("userId") Long userId, @Param("postId") Long postId);
    List<Bookmark> findByUserId(Long userId);
    void insert(Bookmark bookmark);
    void delete(Long id);
    void deleteByUserAndPost(@Param("userId") Long userId, @Param("postId") Long postId);
}
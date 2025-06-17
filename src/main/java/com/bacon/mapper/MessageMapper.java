package com.bacon.mapper;

import com.bacon.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MessageMapper {
    Message findById(Long id);
    List<Message> findConversation(@Param("userId1") Long userId1, @Param("userId2") Long userId2);
    List<Message> findUserConversations(Long userId);
    List<Message> findUnreadMessages(Long userId);
    void insert(Message message);
    void markAsRead(Long id);
    void delete(Long id);
}
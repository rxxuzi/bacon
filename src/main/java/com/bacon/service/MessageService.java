package com.bacon.service;

import com.bacon.entity.Message;
import com.bacon.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    public Message findById(Long id) {
        return messageMapper.findById(id);
    }

    public List<Message> getConversation(Long userId1, Long userId2) {
        return messageMapper.findConversation(userId1, userId2);
    }

    public List<Message> getUserConversations(Long userId) {
        return messageMapper.findUserConversations(userId);
    }

    public List<Message> getUnreadMessages(Long userId) {
        return messageMapper.findUnreadMessages(userId);
    }

    public Message sendMessage(Long senderId, Long receiverId, String content) {
        Message message = new Message(senderId, receiverId, content);
        messageMapper.insert(message);
        return message;
    }

    public void markAsRead(Long id) {
        messageMapper.markAsRead(id);
    }

    public void deleteMessage(Long id) {
        messageMapper.delete(id);
    }
}
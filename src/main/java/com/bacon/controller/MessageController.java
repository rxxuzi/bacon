package com.bacon.controller;

import com.bacon.entity.Message;
import com.bacon.entity.User;
import com.bacon.service.MessageService;
import com.bacon.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping("/message")
    public String messageList(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }

        List<Message> conversations = messageService.getUserConversations(currentUser.getId());
        model.addAttribute("conversations", conversations);
        model.addAttribute("user", currentUser);
        model.addAttribute("currentPath", "/message");
        return "message";
    }

    @GetMapping("/message/{userId}")
    public String messageDetail(@PathVariable String userId, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }

        User otherUser = userService.findByUserId(userId);
        if (otherUser == null) {
            return "redirect:/message";
        }

        List<Message> messages = messageService.getConversation(currentUser.getId(), otherUser.getId());

        // Mark messages as read
        for (Message msg : messages) {
            if (msg.getReceiverId().equals(currentUser.getId()) && !msg.getIsRead()) {
                messageService.markAsRead(msg.getId());
            }
        }

        model.addAttribute("messages", messages);
        model.addAttribute("user", currentUser);
        model.addAttribute("otherUser", otherUser);
        model.addAttribute("currentPath", "/message");
        return "conversation";
    }

    @PostMapping("/message/send")
    @ResponseBody
    public String sendMessage(@RequestParam Long receiverId,
                              @RequestParam String content,
                              HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "error";
        }

        messageService.sendMessage(currentUser.getId(), receiverId, content);
        return "success";
    }
}
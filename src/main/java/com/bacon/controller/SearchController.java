package com.bacon.controller;

import com.bacon.entity.Post;
import com.bacon.entity.User;
import com.bacon.service.PostService;
import com.bacon.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String q, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }

        if (q != null && !q.trim().isEmpty()) {
            List<User> users = userService.searchUsers(q);
            List<Post> posts = postService.searchPosts(q);

            model.addAttribute("users", users);
            model.addAttribute("posts", posts);
            model.addAttribute("query", q);
        }

        model.addAttribute("user", currentUser);
        model.addAttribute("currentPath", "/search");
        return "search";
    }

    @PostMapping("/search")
    public String searchPost(@RequestParam String q) {
        return "redirect:/search?q=" + q;
    }
}
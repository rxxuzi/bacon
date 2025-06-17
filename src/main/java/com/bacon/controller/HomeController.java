package com.bacon.controller;

import com.bacon.entity.Post;
import com.bacon.entity.User;
import com.bacon.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }

        List<Post> posts = postService.getTimeline();
        model.addAttribute("posts", posts);
        model.addAttribute("user", currentUser);
        model.addAttribute("currentPath", "/home");
        return "home";
    }

    @PostMapping("/post/create")
    @ResponseBody
    public String createPost(@RequestParam String content,
                             @RequestParam(defaultValue = "public") String visibility,
                             HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "error";
        }

        postService.createPost(currentUser.getId(), content, visibility);
        return "success";
    }

    @DeleteMapping("/post/{id}")
    @ResponseBody
    public String deletePost(@PathVariable Long id, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "error";
        }

        Post post = postService.findById(id);
        if (post != null && post.getUserId().equals(currentUser.getId())) {
            postService.deletePost(id);
            return "success";
        }
        return "error";
    }
}
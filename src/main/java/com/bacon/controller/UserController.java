package com.bacon.controller;

import com.bacon.entity.Bookmark;
import com.bacon.entity.Post;
import com.bacon.entity.User;
import com.bacon.service.BookmarkService;
import com.bacon.service.PostService;
import com.bacon.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping("/u/{userId}")
    public String profile(@PathVariable String userId, Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }

        User profileUser = userService.findByUserId(userId);
        if (profileUser == null) {
            return "redirect:/home";
        }

        List<Post> posts = postService.findByUserId(profileUser.getId());
        model.addAttribute("profileUser", profileUser);
        model.addAttribute("posts", posts);
        model.addAttribute("user", currentUser);
        model.addAttribute("isOwnProfile", currentUser.getId().equals(profileUser.getId()));
        model.addAttribute("currentPath", "/u/" + userId);
        return "profile";
    }

    @GetMapping("/bookmark")
    public String bookmarks(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }

        List<Bookmark> bookmarks = bookmarkService.getUserBookmarks(currentUser.getId());
        model.addAttribute("bookmarks", bookmarks);
        model.addAttribute("user", currentUser);
        model.addAttribute("currentPath", "/bookmark");
        return "bookmark";
    }

    @PostMapping("/bookmark/add")
    @ResponseBody
    public String addBookmark(@RequestParam Long postId, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "error";
        }

        Bookmark bookmark = bookmarkService.addBookmark(currentUser.getId(), postId);
        return bookmark != null ? "success" : "already_bookmarked";
    }

    @PostMapping("/bookmark/remove")
    @ResponseBody
    public String removeBookmark(@RequestParam Long postId, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "error";
        }

        bookmarkService.removeBookmark(currentUser.getId(), postId);
        return "success";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam String username,
                                @RequestParam(required = false) String bio,
                                @RequestParam(required = false) String color,
                                HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/login";
        }

        userService.updateProfile(currentUser.getId(), username, bio, color);

        // Update session
        currentUser.setUsername(username);
        currentUser.setBio(bio);
        if (color != null) currentUser.setColor(color);

        return "redirect:/u/" + currentUser.getUserId();
    }
}
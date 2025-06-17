package com.bacon.controller;

import com.bacon.entity.User;
import com.bacon.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginForm(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        User user = userService.authenticate(userId, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/home";
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String userId,
                           @RequestParam String username,
                           @RequestParam String password,
                           HttpSession session,
                           Model model) {
        try {
            User user = userService.register(userId, username, password);
            session.setAttribute("user", user);
            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
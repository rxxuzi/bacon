package com.bacon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register", "/css/**", "/js/**", "/images/**");
    }

    private static class AuthInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HttpSession session = request.getSession();
            String path = request.getRequestURI();

            // Allow access to login and register pages
            if (path.equals("/login") || path.equals("/register") ||
                    path.startsWith("/css/") || path.startsWith("/js/") || path.startsWith("/images/")) {
                return true;
            }

            // Check if user is logged in
            if (session.getAttribute("user") == null) {
                response.sendRedirect("/login");
                return false;
            }

            return true;
        }
    }
}
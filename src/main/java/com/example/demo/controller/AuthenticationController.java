package com.example.demo.controller;

import com.example.demo.dto.LoginReqDto;
import com.example.demo.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginReqDto loginRequest, HttpServletResponse response) {
        // Replace with actual user authentication logic
        if (authenticate(loginRequest.getUsername(), loginRequest.getPassword())) {
            return jwtUtil.generateToken(loginRequest.getUsername());
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "Invalid credentials";
        }
    }

    private boolean authenticate(String username, String password) {
        // Simple authentication logic, replace with your own
        return "user".equals(username) && "password".equals(password);
    }
}

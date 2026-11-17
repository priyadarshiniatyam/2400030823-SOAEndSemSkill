package com.library.bookservice.controller;

import com.library.bookservice.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");
        String role;

        if ("admin".equals(username) && "admin123".equals(password)) {
            role = "ADMIN";
        } else if ("librarian".equals(username)
                && "lib123".equals(password)) {
            role = "LIBRARIAN";
        } else if ("user".equals(username)
                && "user123".equals(password)) {
            role = "USER";
        } else {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid username or password");
        }

        String token = jwtService.generateToken(username, role);

        return Map.of(
                "username", username,
                "role", role,
                "token", token,
                "tokenType", "Bearer");
    }
}
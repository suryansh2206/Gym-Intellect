package com.knowit.gymintellect.gym_intellect.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.gymintellect.gym_intellect.entity.User;
import com.knowit.gymintellect.gym_intellect.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class ResetPasswordController {

    @Autowired
    private UserService userService;

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
    	String token = request.get("token");
        String newPassword = request.get("newPassword");

        // Validate token
        User user = userService.findByResetToken(token);
        if (user == null || userService.isTokenExpired(token)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");
        }

        // Update password directly
        userService.updatePassword(user, newPassword);
        return ResponseEntity.ok("Password reset successful.");
    }
//    @PostMapping("/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request, 
//                                                @CookieValue("resetToken") String token) {
//        String newPassword = request.get("newPassword");
//
//        // Validate token
//        User user = userService.findByResetToken(token);
//        if (user == null || userService.isTokenExpired(token)) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");
//        }
//
//        // Update password
//        userService.updatePassword(user, newPassword);
//
//        // Clear the token cookie after successful reset
//        ResponseCookie clearCookie = ResponseCookie.from("resetToken", "")
//                .httpOnly(true)
//                .secure(true)
//                .path("/")
//                .maxAge(0) // Expire immediately
//                .build();
//
//        return ResponseEntity.ok()
//                .header("Set-Cookie", clearCookie.toString())
//                .body("Password reset successful.");
//    }

}


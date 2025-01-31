package com.knowit.gymintellect.gym_intellect.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowit.gymintellect.gym_intellect.entity.User;
import com.knowit.gymintellect.gym_intellect.service.EmailService;
import com.knowit.gymintellect.gym_intellect.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class ForgetController {
	
	@Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;
	
	@PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");

        // Check if user exists
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Generate reset token
        String token = UUID.randomUUID().toString();
        userService.savePasswordResetToken(user, token);
        
        // Send email
        String resetUrl = "http://localhost:3000/reset-password?token=" + token;
        emailService.sendEmail(email, "Password Reset Request", 
            "Click the link to reset your password: " + resetUrl);

        return ResponseEntity.ok("Password reset email sent.");
    }
//    @PostMapping("/forgot-password")
//    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
//        String email = request.get("email");
//
//        // Check if user exists
//        User user = userService.findByEmail(email);
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//
//        // Generate reset token
//        String token = UUID.randomUUID().toString();
//        userService.savePasswordResetToken(user, token);
//
//        // Send email with reset URL (without token)
//        String resetUrl = "http://localhost:3000/reset-password";
//        emailService.sendEmail(email, "Password Reset Request", 
//            "Click the link to reset your password: " + resetUrl);
//
//        // Set token as HTTP-only cookie
//        ResponseCookie cookie = ResponseCookie.from("resetToken", token)
//                .httpOnly(true)
//                .secure(true) // Ensure this is true in production (HTTPS)
//                .path("/")
//                .maxAge(15 * 60) // Token valid for 15 minutes
//                .build();
//
//        return ResponseEntity.ok()
//                .header("Set-Cookie", cookie.toString())
//                .body("Password reset email sent.");
//    }
//    
// // New /validate-reset-token endpoint
//    @GetMapping("/validate-reset-token")
//    public ResponseEntity<String> validateResetToken(@CookieValue("resetToken") String token) {
//        // Validate token
//        User user = userService.findByResetToken(token);
//        if (user == null || userService.isTokenExpired(token)) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token");
//        }
//
//        return ResponseEntity.ok("Token is valid");
//    }

}

//package com.knowit.gymintellect.gym_member.gym_member.controller;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.knowit.gymintellect.gym_member.gym_member.entity.User;
//import com.knowit.gymintellect.gym_member.gym_member.service.UserService;
//
//
//@RestController
//@RequestMapping("/api/membersprofile")
//public class UserController {
//	private final UserService ownerService;
//
////    public UserController(UserService ownerService) {
////        this.ownerService = ownerService;
////    }
//
////    @GetMapping("/{ownerId}")
////    public ResponseEntity<?> getOwnerById(@PathVariable Long ownerId) {
////        Optional<User> owner = ownerService.getOwnerById(ownerId);
////        if(owner.isEmpty()) {
////        	return ResponseEntity.notFound().build();
////        }
////        
////        return ResponseEntity.ok(owner);
////    }
//}
//	

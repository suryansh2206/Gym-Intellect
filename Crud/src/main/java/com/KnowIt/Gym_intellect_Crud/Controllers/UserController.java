package com.KnowIt.Gym_intellect_Crud.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KnowIt.Gym_intellect_Crud.Entity.User;
import com.KnowIt.Gym_intellect_Crud.Services.UserService;

@RestController
@RequestMapping("/api/owners")
public class UserController {
	private final UserService ownerService;

    public UserController(UserService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<?> getOwnerById(@PathVariable Long ownerId) {
        Optional<User> owner = ownerService.getOwnerById(ownerId);
        if(owner.isEmpty()) {
        	return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(owner);
    }
}

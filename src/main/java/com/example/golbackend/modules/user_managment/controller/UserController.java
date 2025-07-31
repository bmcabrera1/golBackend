package com.example.golbackend.modules.user_managment.controller;


import com.example.golbackend.modules.auth.model.User;
import com.example.golbackend.modules.user_managment.dto.ChangePasswordRequest;
import com.example.golbackend.modules.user_managment.dto.MessageResponse;
import com.example.golbackend.modules.user_managment.dto.UpdateRequest;
import com.example.golbackend.modules.user_managment.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usermanagment")
public class UserController {


    @Autowired
    private UserService userService;

    @PatchMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @AuthenticationPrincipal User userDetails,
            @Valid @RequestBody UpdateRequest request) {
        try {
            String email = userDetails.getEmail();
            userService.updateUser(email, request);

            return ResponseEntity.ok(new MessageResponse("User Profile updated successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/changepassword")
    public ResponseEntity<?> changePassword(@AuthenticationPrincipal User userDetails, @Valid @RequestBody ChangePasswordRequest request) {
        try{
            String email = userDetails.getEmail();
            userService.updatePassword(email, request);
            return ResponseEntity.ok(new MessageResponse("Password updated successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/password")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal User userDetails) {
        try{
            userService.deleteUser(userDetails.getEmail());
            return ResponseEntity.ok(new MessageResponse("User deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }


}

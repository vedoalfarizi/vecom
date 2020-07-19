package com.example.vecom.controller;

import java.util.List;
import java.util.UUID;

import com.example.vecom.exception.BadRequestException;
import com.example.vecom.model.User;
import com.example.vecom.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public List<User> getAllUsers() {
    return userService.findAllUsers();
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable UUID id) {
    return userService.findUserById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping("/register")
  public ResponseEntity<String> creatUser(@RequestBody @Validated User user) throws BadRequestException {
    userService.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body("Register success");
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<String> updateUser(@PathVariable UUID id, @RequestBody @Validated User user) {
    return userService.findUserById(id).map(userObj -> {
      userObj.setId(id);
      userObj.setFullName(user.getFullname());
      userService.updateUser(userObj);
      return ResponseEntity.ok("Update success");
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
    return userService.findUserById(id).map(user -> {
      userService.deleteUserById(id);
      return ResponseEntity.ok("Delete success");
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
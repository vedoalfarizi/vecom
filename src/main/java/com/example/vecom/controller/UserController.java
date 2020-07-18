package com.example.vecom.controller;

import java.util.List;
import java.util.UUID;

import com.example.vecom.exception.BadRequestException;
import com.example.vecom.model.User;
import com.example.vecom.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private final UserService userService;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userService.findAllUsers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable UUID id) {
    return userService.findUserById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public User creatUser(@RequestBody @Validated User user) throws BadRequestException {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    return userService.registeUser(user);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody @Validated User user) {
    return userService.findUserById(id).map(userObj -> {
      userObj.setId(id);
      userObj.setFullName(user.getFullname());
      return ResponseEntity.ok(userService.updatUser(userObj));
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable UUID id) {
    return userService.findUserById(id).map(user -> {
      userService.deleteUserById(id);
      return ResponseEntity.ok(user);
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
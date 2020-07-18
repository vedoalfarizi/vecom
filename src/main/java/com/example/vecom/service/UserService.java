package com.example.vecom.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.vecom.exception.BadRequestException;
import com.example.vecom.model.User;
import com.example.vecom.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User registeUser(User user) throws BadRequestException {
    Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
    if (userOptional.isPresent()) {
      throw new BadRequestException("Email has been taken");
    }

    return userRepository.save(user);
  }

  // public Optional<User> login(String email, String password) {
  // return userRepository.login(email, password);
  // }

  public User updatUser(User user) {
    return userRepository.save(user);
  }

  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> findUserById(UUID id) {
    return userRepository.findById(id);
  }

  public void deleteUserById(UUID id) {
    userRepository.deleteById(id);
  }

}
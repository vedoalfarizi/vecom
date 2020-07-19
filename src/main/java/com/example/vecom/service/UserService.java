package com.example.vecom.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.vecom.exception.BadRequestException;
import com.example.vecom.model.User;
import com.example.vecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public void save(User user) throws BadRequestException {
    if(!user.getPasswordConfirm().equals(user.getPassword())){
      throw new BadRequestException("Confirm password not match");
    }

    User userResult = userRepository.findByEmail(user.getEmail());
    if(userResult != null){
      throw new BadRequestException("Email has been taken");
    }

    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  public User updateUser(User user) {
    return userRepository.save(user);
  }

  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  public Optional<User> findUserById(UUID id) {
    return userRepository.findById(id);
  }

  public User findByEmail(String email){
    return userRepository.findByEmail(email);
  }

  public void deleteUserById(UUID id) {
    userRepository.deleteById(id);
  }
}
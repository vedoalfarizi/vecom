package com.example.vecom.repository;

import java.util.Optional;
import java.util.UUID;

import com.example.vecom.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  // @Query("SELECT id, fullname, email FROM Users WHERE email=?1 and
  // password=?2")
  // Optional<User> login(String email, String password);

  Optional<User> findByEmail(String email);
}
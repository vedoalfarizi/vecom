package com.example.vecom.repository;

import java.util.Optional;
import java.util.UUID;

import com.example.vecom.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  @Query(value = "SELECT email as username, password FROM users WHERE email=:email and password=:password", nativeQuery = true)
  Optional<User> login(@Param("email") String email, @Param("password") String password);

  User findByEmail(String email);
}
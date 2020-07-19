package com.example.vecom.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

  @Type(type = "uuid-char")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @NotNull(message = "Please provide a full name")
  @Column(name = "fullname", nullable = false, length = 30)
  private String fullname;

  @Email
  @Column(name = "email", nullable = false, unique = true, length = 50)
  private String email;

  @JsonProperty(access = Access.WRITE_ONLY)
  @Column(name = "password", nullable = false, length = 100)
  private String password;

  @Transient
  @JsonProperty(access = Access.WRITE_ONLY)
  private String passwordConfirm;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false)
  private Date createdAt;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullName(String fullname) {
    this.fullname = fullname;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPasswordConfirm() {
    return passwordConfirm;
  }

  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }
}
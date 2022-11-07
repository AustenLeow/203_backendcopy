package com.cs203g1t2.springjwt.models;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private BigDecimal carbonsaved = new BigDecimal("0.00");

  private BigDecimal moneysaved = new BigDecimal("0.00");
  
  private String answer;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String email, String password, BigDecimal carbonsaved, BigDecimal moneysaved, String answer) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.carbonsaved = carbonsaved;
    this.moneysaved = moneysaved;
    this.answer = answer;
  }

  public User(Long id, String username, String email, String password, BigDecimal carbonsaved, BigDecimal moneysaved, String answer) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.carbonsaved = carbonsaved;
    this.moneysaved = moneysaved;
    this.answer = answer;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public BigDecimal getCarbonsaved() {
    return carbonsaved;
  }

  public void setCarbonsaved(BigDecimal carbonsaved) {
    this.carbonsaved = carbonsaved;
  }

  public BigDecimal getMoneysaved(){
    return moneysaved;
  }

  public void setMoneysaved(BigDecimal moneysaved) {
    this.moneysaved = moneysaved;
  }

  public String getAnswer(){
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}

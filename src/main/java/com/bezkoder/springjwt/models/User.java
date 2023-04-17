package com.bezkoder.springjwt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntities{
  @NotBlank
  @Column(name = "username")
  private String username;
  @NotBlank
  @Column(name = "password")
  private String password;
  @NotBlank
  @Column(name = "full_name")
  private String fullName;
  @NotBlank
  @Column(name = "email")
  private String email;

  @NotBlank
  @Column(name = "address")
  private String address;
  @NotBlank
  @Column(name = "phone")
  private String phone;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User(String username, String password, String fullName, String email, String address, String phone) {
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.email = email;
    this.address = address;
    this.phone = phone;
  }
}

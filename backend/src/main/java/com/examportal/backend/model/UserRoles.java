package com.examportal.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class UserRoles {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID user_role_id;

  @ManyToOne()
  @JoinColumn(name="user_id")
  @JsonBackReference
  private  Users users;


  @ManyToOne()
  @JoinColumn(name="roles_id")
  @JsonBackReference
  private Roles roles;

  public UUID getUser_role_id() {
    return user_role_id;
  }

  public void setUser_role_id(UUID user_role_id) {
    this.user_role_id = user_role_id;
  }

  public Users getUsers() {
    return users;
  }

  public void setUsers(Users users) {
    this.users = users;
  }

  public Roles getRoles() {
    return roles;
  }

  public void setRoles(Roles roles) {
    this.roles = roles;
  }

  public UserRoles() {
  }

  public UserRoles(UUID user_role_id, Users users, Roles roles) {
    this.user_role_id = user_role_id;
    this.users = users;
    this.roles = roles;
  }
}

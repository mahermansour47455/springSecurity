package com.maher.section345.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;


@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long role_id;
    private String role;

    public Role() {
    }
    public Role(String role) {
        this.role = role;
    }
    public Long getRole_id() {
        return role_id;
    }
    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role= role;
    }
    @Override
    public String toString() {
        return "Role [role_id=" + role_id + ", role=" + role + "]";
    }
}
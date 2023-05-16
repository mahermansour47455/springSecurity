package com.maher.section345.repos;

import com.maher.section345.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername (String username);
}
package com.crio.readrent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.readrent.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
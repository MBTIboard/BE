package com.example.mbtiboard.repository;

import com.example.mbtiboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmailAndUserMbti(String email,String userMbti);

//    Optional<User> findByUsernameAndAndEmailAndMbti(String username, String email, String mbti);
}
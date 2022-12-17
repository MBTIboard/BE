package com.example.mbtiboard.repository;

import com.example.mbtiboard.entity.Post;
import com.example.mbtiboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
    Optional<Post> findByIdAndUser(Long id, User user);
}

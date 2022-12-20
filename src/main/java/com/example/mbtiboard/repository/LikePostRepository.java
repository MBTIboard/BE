package com.example.mbtiboard.repository;

import com.example.mbtiboard.entity.Post;
import com.example.mbtiboard.entity.LikePost;
import com.example.mbtiboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikePostRepository extends JpaRepository<LikePost, Long>  {
    LikePost findByUserAndPost(User user, Post post);
}

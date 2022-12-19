package com.example.mbtiboard.repository;

import com.example.mbtiboard.entity.Post;
import com.example.mbtiboard.entity.LikesPost;
import com.example.mbtiboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesPostRepository extends JpaRepository<LikesPost, Long>  {
    LikesPost findByUserAndPost(User user, Post post);
}

package com.example.mbtiboard.repository;

import com.example.mbtiboard.entity.Comment;
import com.example.mbtiboard.entity.LikeComment;
import com.example.mbtiboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {

    LikeComment findByUserAndComment(User user, Comment comment);
}

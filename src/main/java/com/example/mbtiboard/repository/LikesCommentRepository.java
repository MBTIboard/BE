package com.example.mbtiboard.repository;

import com.example.mbtiboard.entity.Comment;
import com.example.mbtiboard.entity.LikesComment;
import com.example.mbtiboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesCommentRepository extends JpaRepository<LikesComment, Long> {

    LikesComment findByUserAndComment(User user, Comment comment);
}

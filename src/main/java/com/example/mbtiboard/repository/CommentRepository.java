package com.example.mbtiboard.repository;

import com.example.mbtiboard.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment, Long> {

}


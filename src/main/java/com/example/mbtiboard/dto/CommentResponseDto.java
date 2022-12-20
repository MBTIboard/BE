package com.example.mbtiboard.dto;

import com.example.mbtiboard.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String username;

    private String conmment;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private int likeCount=0;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getUser().getUsername();
        this.conmment = comment.getComment();
        this.createAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.likeCount = comment.getCommentLikes().size();
    }

}

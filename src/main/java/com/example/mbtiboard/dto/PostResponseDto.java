package com.example.mbtiboard.dto;

import com.example.mbtiboard.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String title;
    private String username;
    private String content;

   public PostResponseDto(Post post){
       this.id = post.getId();
       this.createdAt = post.getCreatedAt();
       this.modifiedAt = post.getModifiedAt();
       this.title = post.getTitle();
       this.content = post.getContent();
   }
}

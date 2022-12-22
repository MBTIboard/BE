package com.example.mbtiboard.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PostWithMbtiRequestDto {
    @NotBlank(message = "제목과 게시글 내용과 mbti카테고리를 모두 기입해주세요")
    private String title;
    @NotBlank(message = "제목과 게시글 내용과 mbti카테고리를 모두 기입해주세요")
    private String content;
    @NotBlank(message = "제목과 게시글 내용과 mbti카테고리를 모두 기입해주세요")
    private String cateMbti;
}

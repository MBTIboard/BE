package com.example.mbtiboard.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PostRequestDto {
    @NotBlank(message = "제목과 게시글 내용을 모두 기입해주세요")
    private String title;
    @NotBlank(message = "제목과 게시글 내용을 모두 기입해주세요")
    private String content;
}
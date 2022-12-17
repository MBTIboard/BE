package com.example.mbtiboard.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostWithMbtiRequestDto {
    private String title;
    private String content;
    private String cateMbti;
}

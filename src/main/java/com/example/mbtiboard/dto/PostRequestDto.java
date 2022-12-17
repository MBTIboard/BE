package com.example.mbtiboard.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String username;
    private String content;
    private String password;
}

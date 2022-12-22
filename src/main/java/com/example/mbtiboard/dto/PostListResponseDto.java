package com.example.mbtiboard.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostListResponseDto {
    List<PostResponseDto> postList = new ArrayList<>();

    public PostListResponseDto(){

    }

    public void addPost(PostResponseDto responseDto){
        postList.add(responseDto);
    }

}

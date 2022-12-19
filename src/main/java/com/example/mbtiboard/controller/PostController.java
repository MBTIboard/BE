package com.example.mbtiboard.controller;


import com.example.mbtiboard.dto.*;
import com.example.mbtiboard.dto.ResponseDto;
import com.example.mbtiboard.service.PostService;
import com.example.mbtiboard.sevice.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public MsgResponseDto savePost(@RequestBody PostWithMbtiRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        postService.savePost(requestDto,userDetails.getUser());
    }

    @GetMapping("/posts")
    public PostListResponseDto getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @PutMapping("/post/{id}")
    public MsgResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.updatePost(id,requestDto,userDetails);
    }

    @DeleteMapping("/post/{id}")
    public MsgResponseDto deletePost( Long id,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.deletePost(id, userDetails);
    }
}

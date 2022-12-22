package com.example.mbtiboard.controller;


import com.example.mbtiboard.dto.*;
import com.example.mbtiboard.security.UserDetailsImpl;
import com.example.mbtiboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity savePost(@RequestBody @Valid PostWithMbtiRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, Errors errors){
        if (errors.hasErrors()) {
            Map<String, String> validatorResult = postService.validateHandling(errors);
            return ResponseEntity.ok(validatorResult);
        }
        return ResponseEntity.ok(postService.savePost(requestDto,userDetails.getUser()));
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
    public ResponseEntity updatePost(@PathVariable Long id, @RequestBody @Valid PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails,Errors errors){
       if(errors.hasErrors()){
           Map<String, String> validatorResult = postService.validateHandling(errors);
           return ResponseEntity.ok(validatorResult);
       }
       return ResponseEntity.ok(postService.updatePost(id,requestDto,userDetails));
    }

    @DeleteMapping("/post/{id}")
    public MsgResponseDto deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.deletePost(id, userDetails);
    }
}

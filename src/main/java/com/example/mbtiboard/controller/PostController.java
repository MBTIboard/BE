package com.example.mbtiboard.controller;


import com.example.mbtiboard.dto.PostRequestDto;
import com.example.mbtiboard.dto.PostResponseDto;
import com.example.mbtiboard.entity.Post;
import com.example.mbtiboard.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //전체 게시글 목록 조회
    @GetMapping("/api/posts")
    public List<Post> getPosts(){ return postService.getPosts();}

    //게시글 작성 postmapping
    @PostMapping("/api/post")
    public Post createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);
    }

    //선택한 게시글 조회
    @GetMapping("/api/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    //선택한 게시글 수정
    @PutMapping("/api/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.updatePost(id,requestDto);
    }

    //선택한 게시글 삭제
    @DeleteMapping("/api/post/{id}") //??밑에 매개변수로 무슨 dto로 받아야하지??
    public ResponseEntity<?> deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto ){
        postService.deletePost(id,requestDto.getPassword());
        return  ResponseEntity.status(HttpStatus.OK).body("string");
    } //??return 해주는 타입을 String으로 하는 방법도 있지 않나?? >> http 상태코드를 보내서


}

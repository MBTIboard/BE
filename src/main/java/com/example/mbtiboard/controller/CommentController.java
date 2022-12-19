package com.example.mbtiboard.controller;

import com.example.mbtiboard.dto.CommentRequestDto;
import com.example.mbtiboard.dto.ResponseDto;
import com.example.mbtiboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseDto createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        return commentService.createComment(postId, requestDto, request);
    }
    //댓글 수정
    @PutMapping("/{id}")
    public ResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        return commentService.updateComment(id, requestDto, request);
    }
    //댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseDto deleteComment(@PathVariable Long id, HttpServletRequest request) {
        return commentService.deleteComment(id, request);
    }
}

package com.example.mbtiboard.controller;

import com.example.mbtiboard.dto.CommentRequestDto;
import com.example.mbtiboard.dto.MsgResponseDto;
import com.example.mbtiboard.security.UserDetailsImpl;
import com.example.mbtiboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/{postId}")
    public MsgResponseDto createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(postId, requestDto, userDetails.getUser());
    }
    //댓글 수정
    @PutMapping("/{id}")
    public MsgResponseDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(id, requestDto, userDetails.getUser());
    }
    //댓글 삭제
    @DeleteMapping("/{id}")
    public MsgResponseDto deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(id,  userDetails.getUser());
    }
}

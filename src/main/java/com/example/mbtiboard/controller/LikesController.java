package com.example.mbtiboard.controller;

import com.example.mbtiboard.dto.ResponseDto;
import com.example.mbtiboard.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikesController {
    private final LikesService likesService;

    @PostMapping("/like/post/{postId}")
    public ResponseDto likesPost(@PathVariable Long postId, HttpServletRequest request) {
        return likesService.likePost(postId, request);
    }

    @PostMapping ("/like/comment/{commentId}")
    public ResponseDto likesComment(@PathVariable Long commentId, HttpServletRequest request) {
        return likesService.likeComment(commentId, request);
    }
}

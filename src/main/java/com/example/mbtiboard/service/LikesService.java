package com.example.mbtiboard.service;

import com.example.mbtiboard.Util.UserUtil;
import com.example.mbtiboard.dto.ResponseDto;
import com.example.mbtiboard.entity.*;
import com.example.mbtiboard.repository.LikesCommentRepository;
import com.example.mbtiboard.repository.CommentRepository;
import com.example.mbtiboard.repository.LikesPostRepository;
import com.example.mbtiboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesPostRepository likesPostRepository;
    private final LikesCommentRepository likesCommentRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserUtil userUtil;

    @Transactional
    public ResponseDto likePost(Long postId, HttpServletRequest request) {
        User user = userUtil.getUserInfo(request);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        LikesPost postLikes = likesPostRepository.findByUserAndPost(user, post);
        if (postLikes == null) {
            LikesPost postLike = new LikesPost(user, post);
            likesPostRepository.save(postLike);
            return new ResponseDto("게시글 좋아요", HttpStatus.OK.value());
        } else {
            likesPostRepository.deleteById(postLikes.getId());
            return new ResponseDto("게시글 좋아요 취소", HttpStatus.OK.value());
        }
    }

    @Transactional
    public ResponseDto likeComment(Long commentId, HttpServletRequest request) {
        User user = userUtil.getUserInfo(request);
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );

        LikesComment commentLikes = likesCommentRepository.findByUserAndComment(user, comment);
        if (commentLikes == null) {
            LikesComment commentLike = new LikesComment(user, comment);
            likesCommentRepository.save(commentLike);
            return new ResponseDto("댓글 좋아요", HttpStatus.OK.value());
        } else {
            likesCommentRepository.deleteById(commentLikes.getId());
            return new ResponseDto("댓글 좋아요 취소", HttpStatus.OK.value());
        }
    }
}

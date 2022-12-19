package com.example.mbtiboard.service;

import com.example.mbtiboard.Util.UserUtil;
import com.example.mbtiboard.dto.CommentRequestDto;
import com.example.mbtiboard.dto.ResponseDto;
import com.example.mbtiboard.entity.Comment;
import com.example.mbtiboard.entity.Post;
import com.example.mbtiboard.entity.User;
import com.example.mbtiboard.repository.CommentRepository;
import com.example.mbtiboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserUtil userUtil;

    @Transactional
    public ResponseDto createComment(Long id, CommentRequestDto requestDto, HttpServletRequest request) {
        User user = userUtil.getUserInfo(request);
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        Comment comment = new Comment(requestDto, post, user);
        commentRepository.save(comment);
        return new ResponseDto("댓글 작성 성공", HttpStatus.OK.value());
    }

    @Transactional
    public ResponseDto updateComment(Long id, CommentRequestDto requestDto, HttpServletRequest request) {
        User user = userUtil.getUserInfo(request);
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );

        String commentWriter = comment.getUser().getUsername();

        if (commentWriter.equals(user.getUsername())) {
            comment.update(requestDto);
            return new ResponseDto("댓글 수정 성공", HttpStatus.OK.value());
        } else {
            return new ResponseDto("댓글 수정 실패", HttpStatus.BAD_REQUEST.value());
        }
    }

    @Transactional
    public ResponseDto deleteComment(Long id, HttpServletRequest request) {
        User user = userUtil.getUserInfo(request);
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );

        String commentWriter = comment.getUser().getUsername();

        if (commentWriter.equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN)) {
            commentRepository.deleteById(id);
            return new ResponseDto("댓글 삭제 성공", HttpStatus.OK.value());
        } else {
            return new ResponseDto("댓글 삭제 실패", HttpStatus.BAD_REQUEST.value());
        }
    }

}

package com.example.mbtiboard.service;

import com.example.mbtiboard.dto.CommentRequestDto;
import com.example.mbtiboard.dto.MsgResponseDto;
import com.example.mbtiboard.entity.Comment;
import com.example.mbtiboard.entity.Post;
import com.example.mbtiboard.entity.User;
import com.example.mbtiboard.entity.UserRoleEnum;
import com.example.mbtiboard.repository.CommentRepository;
import com.example.mbtiboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public MsgResponseDto createComment(Long id, CommentRequestDto requestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        Comment comment = new Comment(requestDto, post, user);
        commentRepository.save(comment);
        return new MsgResponseDto("댓글 작성 성공", HttpStatus.OK.value());
    }

    @Transactional
    public MsgResponseDto updateComment(Long id, CommentRequestDto requestDto, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );

        String commentWriter = comment.getUser().getUsername();

        if (commentWriter.equals(user.getUsername())) {
            comment.update(requestDto);
            return new MsgResponseDto("댓글 수정 성공", HttpStatus.OK.value());
        } else {
            throw new IllegalArgumentException("댓글 작성자가 아닙니다.");
        }
    }

    @Transactional
    public MsgResponseDto deleteComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );

        String commentWriter = comment.getUser().getUsername();

        if (commentWriter.equals(user.getUsername()) || user.getRole().equals(UserRoleEnum.ADMIN)) {
            commentRepository.deleteById(id);
            return new MsgResponseDto("댓글 삭제 성공", HttpStatus.OK.value());
        } else {
            throw new IllegalArgumentException("댓글 작성자 또는 관리자가 아닙니다.");
        }
    }

}

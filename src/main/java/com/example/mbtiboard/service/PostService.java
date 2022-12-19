package com.example.mbtiboard.service;

import com.example.mbtiboard.dto.*;
import com.example.mbtiboard.dto.ResponseDto;
import com.example.mbtiboard.entity.Post;
import com.example.mbtiboard.entity.User;
import com.example.mbtiboard.jwt.JwtUtil;
import com.example.mbtiboard.repository.PostRepository;
import com.example.mbtiboard.repository.UserRepository;
import com.example.mbtiboard.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseDto savePost(PostWithMbtiRequestDto requestDto, User user){
        Post post = postRepository.saveAndFlush(new Post(requestDto,user));
        postRepository.save(post);
        return new ResponseDto("Post 성공", HttpStatus.OK.value());
    }

    @Transactional(readOnly = true)
    public PostListResponseDto getPosts(){
        PostListResponseDto postListResponseDto = new PostListResponseDto();
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        for(Post post : posts){
            postListResponseDto.addPost(new PostResponseDto(post));
        }
        return postListResponseDto;
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("해당 글이 없습니다.")
        );
        return new PostResponseDto(post);
    }


    @Transactional
    public ResponseDto updatePost(Long id, PostRequestDto requestDto, UserDetailsImpl userDetails) {
        if(postRepository.existsByIdAndUser(id,userDetails.getUser() )) {
            Post post = postRepository.findByIdAndUser(id, userDetails.getUser()).orElseThrow(
                    () -> new RuntimeException("해당 글이 없습니다.")
            );
            post.update(requestDto);
            return new ResponseDto("게시글 수정 성공",HttpStatus.OK.value());
        }else{
            return new ResponseDto("게시글 수정 실패",HttpStatus.BAD_REQUEST.value());
        }

    }
    @Transactional
    public ResponseDto deletePost(Long id, UserDetailsImpl userDetails) {
        if(postRepository.existsByIdAndUser(id,userDetails.getUser() )) {
            Post post = postRepository.findByIdAndUser(id, userDetails.getUser()).orElseThrow(
                    () -> new RuntimeException("해당 글이 없습니다.")
            );
            postRepository.delete(post);
            return  new ResponseDto("게시글 삭제 성공",HttpStatus.OK.value());
        }else{
            return new ResponseDto("게시글 삭제 실패",HttpStatus.BAD_REQUEST.value() );
        }

    }
}

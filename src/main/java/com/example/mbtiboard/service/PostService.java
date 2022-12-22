package com.example.mbtiboard.service;

import com.example.mbtiboard.dto.*;
import com.example.mbtiboard.entity.Post;
import com.example.mbtiboard.entity.User;
import com.example.mbtiboard.entity.UserRoleEnum;
import com.example.mbtiboard.repository.PostRepository;
import com.example.mbtiboard.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public MsgResponseDto savePost(PostWithMbtiRequestDto requestDto, User user){
        Post post = postRepository.saveAndFlush(new Post(requestDto,user));
        postRepository.save(post);
        return new MsgResponseDto("Post 성공", HttpStatus.OK.value());
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
    public MsgResponseDto updatePost(Long id, PostWithMbtiRequestDto requestDto, UserDetailsImpl userDetails) {
        if(postRepository.existsByIdAndUser(id,userDetails.getUser() )) {
            Post post = postRepository.findByIdAndUser(id, userDetails.getUser()).orElseThrow(
                    () -> new RuntimeException("해당 글이 없습니다.")
            );
            post.update(requestDto);
            return new MsgResponseDto("게시글 수정 성공",HttpStatus.OK.value());
        }else{
            return new MsgResponseDto("게시글 수정 실패",HttpStatus.BAD_REQUEST.value());
        }

    }
    @Transactional
    public MsgResponseDto deletePost(Long id, UserDetailsImpl userDetails) {
       if(postRepository.existsById(id)){
           Post post;
           if(userDetails.getUser().getRole().equals(UserRoleEnum.ADMIN)) {

               post = postRepository.findById(id).orElseThrow(
                       () -> new RuntimeException("해당 글이 없습니다.")
               );

           }else{
               post = postRepository.findByIdAndUser(id, userDetails.getUser()).orElseThrow(
                       () -> new RuntimeException("해당 글이 없습니다.")
               );
           }
           postRepository.delete(post);
           return  new MsgResponseDto("게시글 삭제 성공",HttpStatus.OK.value());
    }else{
           return  new MsgResponseDto("게시글 삭제 실패",HttpStatus.BAD_REQUEST.value());
       }

    }

}

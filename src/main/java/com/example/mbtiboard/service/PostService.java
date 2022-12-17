package com.example.mbtiboard.sevice;

import com.example.mbtiboard.dto.*;
import com.example.mbtiboard.dto.ResponseDto;
import com.example.mbtiboard.entity.Post;
import com.example.mbtiboard.entity.User;
import com.example.mbtiboard.repository.PostRepository;
import com.example.mbtiboard.repository.UserRepository;
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
    private final ServiceConfig serviceConfig;

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
    public MsgResponseDto updatePost(Long id, PostRequestDto requestDto, UserDetailsImpl userDetails) {
        Post post = postRepository.findByIdAndUser(id, userDetails.getUser()).orElseThrow(
                () -> new RuntimeException("해당 글이 없습니다.")
        );
        post.update(requestDto);
        return new MsgResponseDto("게시글 수정 성공",HttpStatus.OK.value());
    }
}

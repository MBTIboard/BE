package com.example.mbtiboard.service;

import com.example.mbtiboard.dto.PostRequestDto;
import com.example.mbtiboard.dto.PostResponseDto;
import com.example.mbtiboard.entity.Post;
import com.example.mbtiboard.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
   //리포시토리와 연결
    private final PostRepository postRepository;

    //게시글 get
    @Transactional(readOnly = true)
    public List<Post> getPosts() {

        List<Post> allMemoByOrderByModifiedAtDesc = postRepository.findAllMemoByOrderByModifiedAtDesc();

        return allMemoByOrderByModifiedAtDesc;
    }
    //게시글 post

    //특정 게시글 조회
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    //특정게시글 수정 //if문 써서 id의 존재 확인 코드


    //특정게시글삭제 //id값 확인
    @Transactional
    public Long deletePost(Long id, String password){
        System.out.println("게시글 삭제 id,password : "+id +","+password);
       if(postRepository.existsByIdAndPassword(id, password)){
           postRepository.deleteById(id);
           System.out.println("해당 id값에 해당하는 게시글 삭제 완료");
           return id;
       }
        System.out.println("게시글 삭제 id,password : 작동하지 않음") ;
        return id;
    }


}

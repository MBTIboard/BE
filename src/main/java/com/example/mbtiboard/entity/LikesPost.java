package com.example.mbtiboard.entity;

import javax.persistence.*;

public class LikesPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(nullable = false)
    private boolean likeCheck;


    public LikesPost(User user, Post post) {
        this.user = user;
        this.post = post;
        this.likeCheck = true;
    }

    public void likeCancel() {
        this.likeCheck=false;
    }

    public void likepost() {
        this.likeCheck = true;
    }
}

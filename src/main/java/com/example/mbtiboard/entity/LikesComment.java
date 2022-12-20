package com.example.mbtiboard.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class LikesComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @Column(nullable = false)
    private boolean likeCheck;

    public LikesComment(User user, Comment comment) {
        this.user=user;
        this.comment=comment;
        this.likeCheck=true;
    }

    public void likeCancel() { this.likeCheck=false; }
    public void likeComment() { this.likeCheck=true; }

}
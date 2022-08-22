package com.week07.hanghaeinside.domain.unheart;

import com.week07.hanghaeinside.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class UnHeart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne
    private Post post;

    @Column
    private Long postUnHeart;

    //싫어요 누른 memberNickname
    @Column
    private String unHeartBy;
}

package com.week07.hanghaeinside.domain.comment;

import com.week07.hanghaeinside.domain.Timestamped;
import com.week07.hanghaeinside.domain.comment.dto.CommentRequestDto;
import com.week07.hanghaeinside.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column
    private String nickname;

    @Column
    private String password;

    @Lob
    @Column(nullable = false)
    private String content;

    public void update(CommentRequestDto commentRequestDto){
        this.content = commentRequestDto.getContent();
    }
}


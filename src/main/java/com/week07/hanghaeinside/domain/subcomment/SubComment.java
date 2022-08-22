package com.week07.hanghaeinside.domain.subcomment;

import com.week07.hanghaeinside.domain.Timestamped;
import com.week07.hanghaeinside.domain.comment.Comment;
import com.week07.hanghaeinside.domain.subcomment.dto.SubCommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class SubComment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commentId", nullable = false)
    private Comment comment;

    @Column
    private String nickname;

    @Column
    private String password;

    @Lob
    @Column
    private String content;

    public void update(SubCommentRequestDto subCommentRequestDto){
        this.content = subCommentRequestDto.getContent();
    }

}

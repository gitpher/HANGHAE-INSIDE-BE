package com.week07.hanghaeinside.domain.post;


import com.week07.hanghaeinside.domain.Timestamped;
import com.week07.hanghaeinside.domain.comment.Comment;
import com.week07.hanghaeinside.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;
import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int heartCnt;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int unHeartCnt;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int viewCnt;

    public void updateHeart(int heartCnt){
        this.heartCnt = heartCnt;
    }

    public void updateUnHeart(int unHeartCnt){
        this.unHeartCnt = unHeartCnt;
    }

    // 작성자
    private String createdById;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column
    private String postImg;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "post")
    private List<Comment> comments;



    public Post(String title, String createdById, String content, String postImg, int heartCnt, int unHeartCnt){
        this.title = title;
        this.createdById = createdById;
        this.content = content;
        this.postImg = postImg;
        this.heartCnt = heartCnt;
        this.unHeartCnt = unHeartCnt;
    }

    public Post(String title, String createdById, String content, int heartCnt, int unHeartCnt){
        this.title = title;
        this.createdById = createdById;
        this.content = content;
        this.heartCnt = heartCnt;
        this.unHeartCnt = unHeartCnt;
    }

    public boolean validateMember(Member member) {
        return !this.createdById.equals(member.getMemberNickname());
    }


    // LOB : 구조화되지 않은 대형 데이터를 저장하는데 사용

}

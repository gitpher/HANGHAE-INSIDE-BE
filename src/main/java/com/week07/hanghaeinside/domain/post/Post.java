package com.week07.hanghaeinside.domain.post;


import com.week07.hanghaeinside.domain.Member;
import com.week07.hanghaeinside.domain.Timestamped;
import com.week07.hanghaeinside.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column
    private Long heart;

    @Column
    private Long unHeart;

    public void updateHeart(Long heart){
        this.heart = heart;
    }

    public void updateUnHeart(Long heart){
        this.heart = heart;
    }

    public Post(String title, String createdById,String content, String postImg, Long heart, Long unHeart){
        this.title = title;
        this.createdById = createdById;
        this.content = content;
        this.postImg = postImg;
        this.heart = heart;
        this.unHeart = unHeart;
    }

    public Post(String title, String createdById,String content, Long heart, Long unHeart){
        this.title = title;
        this.createdById = createdById;
        this.content = content;
        this.heart = heart;
        this.unHeart = unHeart;
    }

    public boolean validateMember(Member member) {
        return !this.createdById.equals(member.getNickname());
    }


    // LOB : 구조화되지 않은 대형 데이터를 저장하는데 사용

}

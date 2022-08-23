package com.week07.hanghaeinside.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.week07.hanghaeinside.domain.member.Member;
import com.week07.hanghaeinside.domain.post.Post;
import com.week07.hanghaeinside.domain.post.dto.PostDetailsResponseDto;
import com.week07.hanghaeinside.domain.post.dto.PostRequestDto;
import com.week07.hanghaeinside.domain.post.dto.PostResponseDto;
import com.week07.hanghaeinside.repository.CommentRepository;
import com.week07.hanghaeinside.repository.PostRepository;
import com.week07.hanghaeinside.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {


    @Value("jiyoungbucket") // 내 S3 버켓 이름
    private String bucketName;

    private final AmazonS3Client amazonS3Client;

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // 게시물 생성
    @Transactional
    public Long createPost(PostRequestDto postRequestDto, Member member) throws IOException {

        MultipartFile multipartFile = postRequestDto.getPostImg();

        String imgUrl=null;

        if (!multipartFile.isEmpty()) {
            String fileName = CommonUtils.buildFileName(multipartFile.getOriginalFilename());   // 파일이름
            ObjectMetadata objectMetadata = new ObjectMetadata();

            objectMetadata.setContentType(multipartFile.getContentType());  // 파일 타입
            InputStream inputStream = multipartFile.getInputStream();       // throws IOException  예외처리 필요함
            amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));   // S3 저장 및 권한 설정

            imgUrl = amazonS3Client.getUrl(bucketName, fileName).toString();//URI 대입!,URL 변환 시 한글 깨짐

        }

        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .createdById(member.getMemberNickname())
                .content(postRequestDto.getContent())
                .postImg(imgUrl)
                .build();

        return postRepository.save(post).getId();
    }

    // 삭제
    public String deletePost(Long postId, Member member) {

        // 등록 확인
        Post post = isPresentPost(postId);

        // 작성자 확인
        if (post.validateMember(member)) {
            throw new IllegalArgumentException("게시물 작성자만 삭제할 수 있습니다.");
        }

        postRepository.delete(post);

        return "게시물이 삭제되었습니다";

    }

    // 전체 조회
    public Page<PostResponseDto> findAllPost(Pageable pageable) {

        Page<Post> postList = postRepository.findAllByOrderByCreatedAtDesc(pageable);

        Page<PostResponseDto> postResponseDtos = convertToPostResponseDto(postList);

        return postResponseDtos;

    }

    private Page<PostResponseDto> convertToPostResponseDto(Page<Post> postList) {
        List<PostResponseDto> posts = new ArrayList<>();

        for (Post post : postList){
            posts.add(
                    PostResponseDto.builder()
                            .postId(post.getId())
                            .nickname(post.getCreatedById())
                            .title(post.getTitle())
                            .postImg(post.getPostImg())
                            .createAt(post.getCreatedAt())
                            .heartCnt(post.getHeartCnt())
                            .viewCnt(post.getViewCnt())
                            .unHeartCnt(post.getUnHeartCnt())
                            .build()

            );
        }

        return new PageImpl<>(posts,postList.getPageable(),postList.getTotalElements());
    }

    // 상세 조회
    public PostDetailsResponseDto findPost(Long postId) {

        Post post = isPresentPost(postId);

        return getPostDetailsResponseDto(post);

    }

    private PostDetailsResponseDto getPostDetailsResponseDto(Post post) {

        return PostDetailsResponseDto.builder()
                .postId(post.getId())
                .nickname(post.getCreatedById())
                .title(post.getTitle())
                .content(post.getContent())
                .postImg(post.getPostImg())
                .createAt(post.getCreatedAt())
                .heartCnt(post.getHeartCnt())
                .viewCnt(post.getViewCnt())
                .unHeartCnt(post.getUnHeartCnt())
                .build();
    }

    @Transactional
    public int updateView(Long id) {
        return postRepository.updateView(id);
    }


    @Transactional(readOnly = true)
    public Post isPresentPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElseThrow(() -> new IllegalArgumentException("등록되지 않은 게시물입니다."));
    }

    // 개념글 조회
    public Page<PostResponseDto> findAllPostTop(Pageable pageable) {
        int heartCnt = 10;

        Page<Post> postList = postRepository.findAllByHeartCntGreaterThanOrderByCreatedAtDesc(heartCnt, pageable);

        return convertToResponseDto(postList);
    }

    private Page<PostResponseDto> convertToResponseDto(Page<Post> postList) {
        List<PostResponseDto> posts = new ArrayList<>();

        for (Post post : postList) {
            posts.add(
                    PostResponseDto.builder()
                            .postId(post.getId())
                            .nickname(post.getCreatedById())
                            .title(post.getTitle())
                            .postImg(post.getPostImg())
                            .createAt(post.getCreatedAt())
                            .viewCnt(post.getViewCnt())
                            .heartCnt(post.getHeartCnt())
                            .unHeartCnt(post.getUnHeartCnt())
                            .build()
            );
        }
        return new PageImpl(posts, postList.getPageable(), postList.getTotalElements());
    }

}

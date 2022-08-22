package com.week07.hanghaeinside.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.week07.hanghaeinside.domain.Member;
import com.week07.hanghaeinside.domain.post.Post;
import com.week07.hanghaeinside.domain.post.dto.PostRequestDto;
import com.week07.hanghaeinside.domain.post.dto.PostResponseDto;
import com.week07.hanghaeinside.repository.PostRepository;
import com.week07.hanghaeinside.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {


    @Value("jiyoungbucket") // 내 S3 버켓 이름
    private String bucketName;

    private final AmazonS3Client amazonS3Client;

    private final PostRepository postRepository;

    // 게시물 생성
    @Transactional
    public Long createPost(PostRequestDto postRequestDto, Member member) throws IOException {

        MultipartFile multipartFile = postRequestDto.getPostImg();

        String imgUrl;

        String fileName = CommonUtils.buildFileName(multipartFile.getOriginalFilename());   // 파일이름
        ObjectMetadata objectMetadata = new ObjectMetadata();

        objectMetadata.setContentType(multipartFile.getContentType());  // 파일 타입
        InputStream inputStream = multipartFile.getInputStream();       // throws IOException  예외처리 필요함
        amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));   // S3 저장 및 권한 설정

        imgUrl = amazonS3Client.getUrl(bucketName, fileName).toString();//URI 대입!,URL 변환 시 한글 깨짐


        Post post = Post.builder()
                .title(postRequestDto.getTitle())
                .createdById(member.getNickname())
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

        Page<PostResponseDto> postResponseDtos =

    }

    // 상세 조회
    public Object findPost(Long postId) {


    }

    @Transactional(readOnly = true)
    public Post isPresentPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElseThrow(() -> new IllegalArgumentException("등록되지 않은 게시물입니다."));
    }



}

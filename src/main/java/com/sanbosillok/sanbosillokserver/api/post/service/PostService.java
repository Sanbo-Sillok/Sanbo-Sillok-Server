package com.sanbosillok.sanbosillokserver.api.post.service;

import com.sanbosillok.sanbosillokserver.api.post.domain.Post;
import com.sanbosillok.sanbosillokserver.api.post.domain.PostStatus;
import com.sanbosillok.sanbosillokserver.api.post.dto.PostRequest;
import com.sanbosillok.sanbosillokserver.api.post.dto.PostResponse;
import com.sanbosillok.sanbosillokserver.api.post.dto.PostTitleResponse;
import com.sanbosillok.sanbosillokserver.api.post.dto.PostUpdateRequest;
import com.sanbosillok.sanbosillokserver.api.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void create(PostRequest postRequest, String username) {
        if (!postRepository.existsByTitle(postRequest.getTitle())) {

            Post post = Post.builder()
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .lastModifier(username)
                    .status(PostStatus.ACTIVE)
                    .build();

            postRepository.save(post);
        }
    }

    public PostTitleResponse getRandomPostTitle() {
        return new PostTitleResponse(postRepository.findRandomPost());
    }

    public PostResponse getPost(String title) {
        return new PostResponse(postRepository.findByTitle(title)
                    .orElse(Post.builder()
                            .build()));
    }

    public List<PostTitleResponse> getPosts() {
        return postRepository.findAll().stream()
                .map(PostTitleResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(String title, PostUpdateRequest postUpdateRequest, String username) {
        Post updatePost = postRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        updatePost.update(postUpdateRequest.getContent(), username);
    }

    @Transactional
    public void delete(String title) {
        postRepository.delete(postRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")));
    }
}

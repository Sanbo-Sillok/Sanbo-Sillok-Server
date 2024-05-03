package com.sanbosillok.sanbosillokserver.post.service;

import com.sanbosillok.sanbosillokserver.post.domain.Post;
import com.sanbosillok.sanbosillokserver.post.domain.PostStatus;
import com.sanbosillok.sanbosillokserver.post.dto.PostRequest;
import com.sanbosillok.sanbosillokserver.post.dto.PostResponse;
import com.sanbosillok.sanbosillokserver.post.dto.PostTitleResponse;
import com.sanbosillok.sanbosillokserver.post.dto.PostUpdateRequest;
import com.sanbosillok.sanbosillokserver.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void create(PostRequest postRequest, String username) {
        if (!postRepository.existsByTitle(postRequest.getTitle())) {

            Post post = Post.builder()
                    .title(postRequest.getTitle())
                    .contents(postRequest.getContents())
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
        return new PostResponse(postRepository.findByTitle(title));
    }

    public List<PostResponse> getPosts() {
        return postRepository.findAll().stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(String title, PostUpdateRequest postUpdateRequest, String username) {
        Post updatePost = postRepository.findByTitle(title);
        updatePost.update(postUpdateRequest.getContents(), username);
    }

    @Transactional
    public void delete(String title) {
        postRepository.delete(postRepository.findByTitle(title));
    }
}

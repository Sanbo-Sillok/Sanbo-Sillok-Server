package com.sanbosillok.sanbosillokserver.post.controller;

import com.sanbosillok.sanbosillokserver.post.dto.PostRequest;
import com.sanbosillok.sanbosillokserver.post.dto.PostResponse;
import com.sanbosillok.sanbosillokserver.post.dto.PostTitleResponse;
import com.sanbosillok.sanbosillokserver.post.dto.PostUpdateRequest;
import com.sanbosillok.sanbosillokserver.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public void createPost(@RequestBody @Valid PostRequest postRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        postService.create(postRequest, username);
    }

    @GetMapping("/post")
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/post/{title}")
    public PostResponse getPost(@PathVariable String title) {
        return postService.getPost(title);
    }

    @GetMapping("/post/random")
    public PostTitleResponse getRandomPost() {
        return postService.getRandomPostTitle();
    }

    @PatchMapping("/post/{title}")
    public void updatePost(@PathVariable String title,
                           @RequestBody @Valid PostUpdateRequest postUpdateRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        postService.update(title, postUpdateRequest, username);
    }

    @DeleteMapping("/post/{title}")
    public void deletePost(@PathVariable String title) {
        postService.delete(title);
    }
}

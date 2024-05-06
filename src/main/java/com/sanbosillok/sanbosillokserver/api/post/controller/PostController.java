package com.sanbosillok.sanbosillokserver.api.post.controller;

import com.sanbosillok.sanbosillokserver.api.post.dto.PostRequest;
import com.sanbosillok.sanbosillokserver.api.post.dto.PostResponse;
import com.sanbosillok.sanbosillokserver.api.post.dto.PostTitleResponse;
import com.sanbosillok.sanbosillokserver.api.post.dto.PostUpdateRequest;
import com.sanbosillok.sanbosillokserver.api.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public void createPost(@RequestBody @Valid PostRequest postRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        postService.create(postRequest, username);
    }

    @GetMapping("")
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{title}")
    public PostResponse getPost(@PathVariable String title) {
        return postService.getPost(title);
    }

    @GetMapping("/random")
    public PostTitleResponse getRandomPost() {
        return postService.getRandomPostTitle();
    }

    @PatchMapping("/{title}")
    public void updatePost(@PathVariable String title,
                           @RequestBody @Valid PostUpdateRequest postUpdateRequest) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        postService.update(title, postUpdateRequest, username);
    }

    @DeleteMapping("/{title}")
    public void deletePost(@PathVariable String title) {
        postService.delete(title);
    }
}

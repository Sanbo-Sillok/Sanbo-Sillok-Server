package com.sanbosillok.sanbosillokserver.api.post.repository;

import com.sanbosillok.sanbosillokserver.api.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByTitle(String title);

    Boolean existsByTitle(String title);

    @Query("SELECT p FROM Post p ORDER BY RAND() LIMIT 1")
    Post findRandomPost();
}

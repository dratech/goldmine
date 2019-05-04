package com.nebu.goldmine.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository  extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p FROM PostEntity p ORDER BY p.id DESC")
    List<PostEntity> newPosts(Pageable pageable);

    @Query("SELECT p FROM PostEntity p ORDER BY p.upvoteCount DESC")
    List<PostEntity> bestPosts(Pageable pageable);

    @Query("SELECT p FROM PostEntity p WHERE (p.protagonist LIKE %:searchString% OR p.context LIKE %:searchString% OR p.story LIKE %:searchString%) ORDER BY p.id DESC")
    List<PostEntity> newSearch(String searchString, Pageable pageable);

    @Query("SELECT p FROM PostEntity p WHERE (p.protagonist LIKE %:searchString% OR p.context LIKE %:searchString% OR p.story LIKE %:searchString%) ORDER BY p.upvoteCount DESC, p.id ASC")
    List<PostEntity> bestSearch(String searchString, Pageable pageable);

    @Query("SELECT COUNT(p) FROM PostEntity p WHERE (p.protagonist LIKE %:searchString% OR p.context LIKE %:searchString% OR p.story LIKE %:searchString%)")
    Long searchCount(String searchString);
}

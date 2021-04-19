package com.itm.legbook.repository;

import com.itm.legbook.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query(value = "select count(like_id) from likes l where l.post_id=:postId and user_id=:userId",nativeQuery = true)
    int isPostLikedByMe(Long postId,Long userId);
}

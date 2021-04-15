package com.itm.legbook.repository;

import com.itm.legbook.model.Like;
import com.itm.legbook.model.Post;
import com.itm.legbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long>
{

    Optional<Like> findTopByPostAndUser(Post post, User currentUser);

    @Modifying
    @Query(value = "delete from likes l where l.user_id=:userId and post_id=:postId",nativeQuery = true)
    void deleteLikeByUserIdAndPostId(Long userId,Long postId);
}

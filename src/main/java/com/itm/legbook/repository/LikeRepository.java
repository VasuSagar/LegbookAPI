package com.itm.legbook.repository;

import com.itm.legbook.model.Like;
import com.itm.legbook.model.Post;
import com.itm.legbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long>
{

    Optional<Like> findTopByPostAndUser(Post post, User currentUser);
}

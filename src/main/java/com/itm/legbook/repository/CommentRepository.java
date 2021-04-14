package com.itm.legbook.repository;

import com.itm.legbook.model.Comment;
import com.itm.legbook.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPost(Post post);
}

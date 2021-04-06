package com.itm.legbook.repository;

import com.itm.legbook.com.itm.legbook.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}

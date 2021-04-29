package com.itm.legbook.repository;

import com.itm.legbook.model.Message;
import com.itm.legbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepositroy extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "select * from user u where u.user_id not in (:currentUserId)" ,nativeQuery = true)
    List<User> getAllUsers(Long currentUserId);
}

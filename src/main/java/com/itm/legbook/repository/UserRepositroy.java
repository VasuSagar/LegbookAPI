package com.itm.legbook.repository;

import com.itm.legbook.com.itm.legbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositroy extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}

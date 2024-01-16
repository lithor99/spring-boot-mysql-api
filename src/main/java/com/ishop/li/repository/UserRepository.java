package com.ishop.li.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ishop.li.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

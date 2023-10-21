package com.meet.partner.repository;

import com.meet.partner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {
    User save(User user);

    User findByUserID(Long userId);

    User findByEmail(String email);
}

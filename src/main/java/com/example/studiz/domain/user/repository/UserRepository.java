package com.example.studiz.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.studiz.domain.user.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsUserByUsername(String username);


    Optional<User> findBySchoolId(Long schoolid);

    Optional<User> findByUsername(String username);
}

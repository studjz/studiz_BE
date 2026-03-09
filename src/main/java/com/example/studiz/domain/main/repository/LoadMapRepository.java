package com.example.studiz.domain.main.repository;

import com.example.studiz.domain.main.LoadMap;
import com.example.studiz.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoadMapRepository extends JpaRepository<LoadMap, Integer> {


    List<LoadMap> findAllByMajor(String major);
    Optional<LoadMap> findByUser(User user);
}

package com.example.studiz.global.jwt;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisRefreshTokenRepository extends CrudRepository<RedisRefreshToken,Long> {
}

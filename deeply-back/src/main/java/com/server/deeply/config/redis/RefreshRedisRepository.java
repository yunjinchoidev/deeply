package com.server.deeply.config.redis;

import org.springframework.data.repository.CrudRepository;

public interface RefreshRedisRepository extends CrudRepository<RefreshRedisToken, String> {
    boolean existsByRefreshToken(String token);
}

package com.example.demo.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RefreshTokenRepository {

    private final RedisTemplate redisTemplate;

    public RefreshTokenRepository(final RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(final RefreshToken refreshToken) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(String.valueOf(refreshToken.getMemberId()), refreshToken.getRefreshToken());
        redisTemplate.expire(String.valueOf(refreshToken.getMemberId()), 10, TimeUnit.SECONDS);
    }

    public boolean findById(final Integer memberId) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String refreshToken = valueOperations.get(String.valueOf(memberId));

        return refreshToken != null;
    }
}
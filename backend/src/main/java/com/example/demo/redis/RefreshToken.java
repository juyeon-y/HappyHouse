package com.example.demo.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 100000)
public class RefreshToken {

    private final String refreshToken;
    @Id
    private final String memberId;

    public RefreshToken(final String refreshToken, final Integer memberId) {
        this.refreshToken = refreshToken;
        this.memberId = String.valueOf(memberId);
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getMemberId() {
        return memberId;
    }
}
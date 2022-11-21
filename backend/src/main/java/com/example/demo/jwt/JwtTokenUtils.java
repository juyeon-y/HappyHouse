package com.example.demo.jwt;

import com.example.demo.member.Member;
import com.example.demo.redis.RefreshToken;
import com.example.demo.redis.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtTokenUtils {
    private static final String key = "secret";
    private static final long exp = 10*60 * 1000;
    private final RefreshTokenRepository refreshTokenRepository;

    public JwtTokenUtils(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String createTokens(Member  member, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", member.getEmail());
        map.put("id", member.getId());
        map.put("roles", authorities);
        return createTokens(map);
    }

    public String createTokens(Map<String, Object> claims) {
        return createTokens(Jwts.claims(claims));
    }

    public String createTokens(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public void generateRefreshToken(Member member) {

        RefreshToken refreshToken = new RefreshToken(UUID.randomUUID().toString(), member.getId());
        refreshTokenRepository.save(refreshToken);

    }
    public Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }

}

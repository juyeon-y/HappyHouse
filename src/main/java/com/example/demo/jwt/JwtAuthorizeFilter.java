package com.example.demo.jwt;

import com.example.demo.member.Member;
import com.example.demo.redis.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.logging.log4j.util.Strings.isEmpty;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JwtAuthorizeFilter extends BasicAuthenticationFilter {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenUtils jwtTokenUtils;


    public JwtAuthorizeFilter(AuthenticationManager authenticationManager, RefreshTokenRepository refreshTokenRepository, JwtTokenUtils jwtTokenUtils) {
        super(authenticationManager);
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        String bearer_prefix = "Bearer ";
        if (isEmpty(header) || !header.startsWith(bearer_prefix)) {
            chain.doFilter(request, response);
            return;
        }
        String token = header.substring(7);
        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(null, token);
        try {
            Authentication authenticate = this.getAuthenticationManager().authenticate(authenticationToken);
            if (authenticate.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticate);
            }
        }
        catch (ExpiredJwtException e){
            Claims claims = e.getClaims();
            if (refreshTokenRepository.findById((Integer) claims.get("id"))) {
                Member member = Member.builder()
                        .id((Integer) claims.get("id"))
                        .email((String) claims.get("email"))
                        .role("ROLE_USER")
                        .build();
                String tokens = jwtTokenUtils.createTokens(member, member.getAuthorities());
                JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(member.getAuthorities(), token);
                SecurityContextHolder.createEmptyContext().setAuthentication(jwtAuthenticationToken);
                response.setHeader(AUTHORIZATION, "Bearer " + tokens);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }

        }
    }
}



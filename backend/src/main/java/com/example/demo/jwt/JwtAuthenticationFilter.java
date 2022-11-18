package com.example.demo.jwt;


import com.example.demo.member.Member;
import com.example.demo.member.request.MemberLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper om = new ObjectMapper();

        try {
            MemberLoginRequest user = om.readValue(request.getInputStream(), MemberLoginRequest.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        Member principal = (Member) authResult.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
//        new RefreshToken
        jwtTokenUtils.generateRefreshToken(principal);
        String tokens = jwtTokenUtils.createTokens(principal, authorities);
        SecurityContextHolder.createEmptyContext().setAuthentication(authResult);
        response.setHeader(AUTHORIZATION, "Bearer " + tokens);
    }
}

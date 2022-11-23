package com.example.demo.jwt;

import com.example.demo.member.Member;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private static final String KEY_ROLES = "roles";
    private final JwtTokenUtils jwtTokenUtils;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Claims claims = jwtTokenUtils.getAllClaims(((JwtAuthenticationToken) authentication).getToken());
        Collection<? extends GrantedAuthority> grantedAuthorities = createGrantedAuthorities(claims);

        Member member = Member.builder().email((String) claims.get("email")).id((Integer) claims.get("id")).build();
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(grantedAuthorities, "");
        jwtAuthenticationToken.setDetails(member);
        jwtAuthenticationToken.setAuthenticated(true);
        return jwtAuthenticationToken;
    }

    private Collection<? extends GrantedAuthority> createGrantedAuthorities(Claims claims) {
        List roles = (List) claims.get(KEY_ROLES);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Object role : roles) {
            grantedAuthorities.add(() -> (String) role);
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}

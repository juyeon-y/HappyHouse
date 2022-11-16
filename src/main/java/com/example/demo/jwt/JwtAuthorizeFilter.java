package com.example.demo.jwt;

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

public class JwtAuthorizeFilter extends BasicAuthenticationFilter {


    public JwtAuthorizeFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
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
        Authentication authenticate = this.getAuthenticationManager().authenticate(authenticationToken);
        if (authenticate.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }
    }
}



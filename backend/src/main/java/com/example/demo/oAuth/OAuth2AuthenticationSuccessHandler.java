package com.example.demo.oAuth;

import com.example.demo.jwt.JwtTokenUtils;
import com.example.demo.member.Member;
import com.example.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@Component
@Slf4j
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenUtils jwtTokenUtils;
    private final MemberRepository repository;
    private final String targetUrl = "http://localhost:8080";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Member member = repository.findByEmail(oAuth2User.getAttribute("email"));
        jwtTokenUtils.generateRefreshToken(member);
        String tokens = "Bearer " + jwtTokenUtils.createTokens(member, oAuth2User.getAuthorities());
        response.setHeader(AUTHORIZATION, tokens);
        StringBuilder sb = new StringBuilder();
        sb.append(targetUrl);
        sb.append("/user/oauth?");
        sb.append("token=").append(tokens).append("&");
        sb.append("name=").append(member.getName());
        log.info("tartgetUrl {}",sb);
        response.sendRedirect(sb.toString());

        super.onAuthenticationSuccess(request, response, authentication);
    }
}

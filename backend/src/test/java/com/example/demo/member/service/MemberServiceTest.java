//package com.example.demo.member.service;
//
//import com.example.demo.member.Member;
//import com.example.demo.member.repository.MemberRepository;
//import com.example.demo.member.request.MemberLoginRequest;
//import com.example.demo.member.request.MemberRegisterRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@Slf4j
//class MemberServiceTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//
//    ObjectMapper objectMapper;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    MemberService memberService;
//    @Test
//    @DisplayName("회원 검색")
//    void findByEmail() {
//        String email = "a@a.com";
//        Member byEmail = memberRepository.findByEmail(email);
//        log.info("user {}", byEmail.getEmail());
//    }
//
//    @Test
//    @DisplayName("회원 가입")
//    void regist(){
//
//        MemberRegisterRequest memberRegisterRequest = new MemberRegisterRequest("123", "a@a.com", "ㅌㅌ", "ㅂㅈㄷㄱ");
//        memberService.regist(memberRegisterRequest);
//    }
//
//    @Test
//    @DisplayName("1. 토큰 검증")
//    void login() throws Exception {
//        MemberLoginRequest memberLoginRequest = new MemberLoginRequest("a@a.com", "123");
//        MvcResult mvcResult = mockMvc.perform(post("http://localhost:8080/login")
//                        .content(objectMapper.writeValueAsString(memberLoginRequest))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andReturn();
//        MockHttpServletResponse response = mvcResult.getResponse();
//        System.out.println(response);
//        String authorization = "Authorization";
//        String token = response.getHeader(authorization);
//
//        Thread.sleep(1000 * 5);
//        MvcResult mvcResult1 = mockMvc.perform(get("http://localhost:8080/user/test")
//                        .header(authorization, token)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn();
//        String token2 = mvcResult1.getResponse().getHeader(authorization);
//
//        Assertions.assertNotEquals(token, token2);
//
//        Thread.sleep(5000);
//
//        mockMvc.perform(get("http://localhost:8080/user/test")
//                        .header(authorization, token2)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(401))
//                .andDo(print())
//                .andReturn();
//
//
//    }
//
//    @DisplayName("refresh 만료 테스트")
//    @Test
//    void validRefreshToken() throws Exception {
//        MemberLoginRequest memberLoginRequest = new MemberLoginRequest("a@a.com", "123");
//        MvcResult mvcResult = mockMvc.perform(post("http://localhost:8080/login")
//                        .content(objectMapper.writeValueAsString(memberLoginRequest))
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andReturn();
//        MockHttpServletResponse response = mvcResult.getResponse();
//        System.out.println(response);
//        String authorization = "Authorization";
//        String token = response.getHeader(authorization);
//
//        Thread.sleep(1000 * 10);
//        mockMvc.perform(get("http://localhost:8080/user/test")
//                        .header(authorization, token)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(401))
//                .andDo(print());
//    }
//}
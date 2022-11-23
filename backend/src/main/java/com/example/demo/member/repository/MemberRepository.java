package com.example.demo.member.repository;

import com.example.demo.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberRepository {

    Member findByEmail(String email);
    Member findById(Integer id);

    void save(Member member);

    void update(Member member);

    void delete(Integer id);
}

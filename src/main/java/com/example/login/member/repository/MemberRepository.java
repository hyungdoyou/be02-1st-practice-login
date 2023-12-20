package com.example.login.member.repository;

import com.example.login.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Optional<Member> findByEmail(String email);
    public Optional<Member> findByPassword(String password);
}

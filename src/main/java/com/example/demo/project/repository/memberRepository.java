package com.example.demo.project.repository;

import com.example.demo.project.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface memberRepository extends JpaRepository<Member, Long> {
    
    
}
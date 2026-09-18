package com.example.demo.project.service;

import org.springframework.stereotype.Service;
import com.example.demo.project.repository.memberRepository;
import com.example.demo.project.model.Member;

import java.util.List;

@Service
public class memberService {

    private final memberRepository repository;


    public memberService(memberRepository repository) {
        this.repository = repository;
    }

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    public Member addMember(Member member) {
        return repository.save(member);
    }

    public Member getMemberById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public Member updateMember(Long id, Member updatedMember) {
        Member existingMember = repository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        existingMember.setName(updatedMember.getName());
        existingMember.setEmail(updatedMember.getEmail());
        existingMember.setPhone(updatedMember.getPhone());
        return repository.save(existingMember);
    }

    public void deleteMember(Long id) {
        if(!repository.existsById(id)) {
            throw new RuntimeException("Member not found");
        }
        repository.deleteById(id);
    }
}

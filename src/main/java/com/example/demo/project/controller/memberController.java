package com.example.demo.project.controller;

import com.example.demo.project.model.Member;
import com.example.demo.project.service.memberService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController 
@RequestMapping("/members")
public class memberController {
    private final memberService service;
    public memberController(memberService service) {
        this.service = service;
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return service.getAllMembers();
    }

    @PostMapping
    public Member addMember(@RequestBody Member member) {
        return service.addMember(member);
    }

    @PutMapping("/{id}")
    public Member updateMember(
           @PathVariable Long id, 
           @RequestBody Member member) {
    
        return service.updateMember(id, member);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        service.deleteMember(id);
        
    }
    
}

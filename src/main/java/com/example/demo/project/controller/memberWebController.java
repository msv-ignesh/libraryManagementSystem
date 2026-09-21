package com.example.demo.project.controller;

import com.example.demo.project.model.Member;
import com.example.demo.project.service.memberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members-ui")
public class memberWebController {

    private final memberService memberService;

    public memberWebController(memberService memberService) {
        this.memberService = memberService;
    }

    // Show all members
    @GetMapping
    public String showMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "member";
    }

    // Show Add Member form
    @GetMapping("/new")
    public String showAddMemberForm(Model model) {
        model.addAttribute("member", new Member());
        return "addMember";
    }

    // Save new member
    @PostMapping("/save")
    public String saveMember(@ModelAttribute("member") Member member) {

        memberService.addMember(member);

        return "redirect:/members-ui";
    }

    // Show Edit Member form
    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Member member = memberService.getMemberById(id);

        model.addAttribute("member", member);

        return "editMember";
    }

    // Update member
    @PostMapping("/update/{id}")
    public String updateMember(
            @PathVariable Long id,
            @ModelAttribute("member") Member member) {

        memberService.updateMember(id, member);

        return "redirect:/members-ui";
    }

    // Delete member
    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id) {

        memberService.deleteMember(id);

        return "redirect:/members-ui";
    }
}
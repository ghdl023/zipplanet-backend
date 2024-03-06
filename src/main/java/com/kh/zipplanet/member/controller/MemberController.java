package com.kh.zipplanet.member.controller;

import com.kh.zipplanet.global.jwt.TokenDto;
import com.kh.zipplanet.member.model.MemberLoginRequestDto;
import com.kh.zipplanet.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
	private final MemberService memberService;
	 
    @PostMapping("/login")
    public TokenDto login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        TokenDto tokenDto = memberService.login(memberId, password);
        return tokenDto;
    }
    
    @PostMapping("/test")
    public String test() {
    	return "sucess";
    }
}
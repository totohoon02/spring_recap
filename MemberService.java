package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberCreateDTO;
import com.example.demo.domain.MemberDeleteDto;
import com.example.demo.domain.MemberUpdateDTo;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public List<Member> getAll() {
		return memberRepository.findAll();
	}
	
	public Member findMemberById(Long id) {
		Optional<Member> result = memberRepository.findById(id);
		if(result.isPresent()){
			return  result.get();
		}
		else{
			throw new IllegalArgumentException("No such member");
		}
	}
	
	public Member findMemberByName(String name) {
		Optional<Member> result = memberRepository.findByName(name);
		if(result.isPresent()){
			return  result.get();
		}
		else{
			throw new IllegalArgumentException("No such member");
		}
	}
	
	@Transactional
	public Member join(MemberCreateDTO createMember) {
		Member member = new Member();
		member.setName(createMember.getName());
		memberRepository.save(member);
		return member;
	}
	
	@Transactional
	public Member update(MemberUpdateDTo updateMember) {
		Optional<Member> result = memberRepository.findById(updateMember.getId());
		if(result.isPresent()){
			Member member = result.get();
			member.setName(updateMember.getName());
			memberRepository.save(member);
			return member;
		}
		else{
			throw new IllegalArgumentException("No such member");
		}
	}
	
	@Transactional
	public Boolean delete(MemberDeleteDto updateMember) {
		Optional<Member> result = memberRepository.findById(updateMember.getId());
		if(result.isPresent()){
			memberRepository.delete(result.get());
			return true;
		}
		else{
			return false;
		}
	}
}

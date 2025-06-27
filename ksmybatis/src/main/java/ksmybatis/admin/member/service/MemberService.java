package ksmybatis.admin.member.service;

import java.util.List;
import java.util.Map;

import ksmybatis.admin.member.domain.Member;

public interface MemberService {
	
	// 회원 정보 일치 확인
	Map<String, Object> matchMember(String memberId, String memberPw);
	
	// 회원 탈퇴
	void removeMember(String memberId);
	
	// 특정 회원 수정
	void modifyMemberInfo(Member member); 
	
	// 특정 회원 정보 조회
	Member getMemberInfoById(String memberId);
	
	// 회원 등록
	void addMember(Member member);
	
	// 회원 목록 조회
	List<Member> getMemberList();

}

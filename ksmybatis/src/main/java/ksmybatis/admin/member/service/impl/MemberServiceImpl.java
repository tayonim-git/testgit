package ksmybatis.admin.member.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmybatis.admin.member.domain.Member;
import ksmybatis.admin.member.mapper.MemberMapper;
import ksmybatis.admin.member.service.MemberService;
import ksmybatis.admin.orders.mapper.OrdersMapper;
import ksmybatis.admin.products.mapper.ProductsMapper;
import ksmybatis.admin.vendors.mapper.VendorsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor // final field 생성자 메서드 만들어 줌
@Slf4j
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private final VendorsMapper vendorsMapper;
	private final ProductsMapper productsMapper;
	private final OrdersMapper ordersMapper;
	
	@Override
	public Map<String, Object> matchMember(String memberId, String memberPw) {
		boolean isMatched = false;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Member memberInfo = memberMapper.getMemberInfoById(memberId);
		if(memberInfo != null) {
			String checkPw = memberInfo.getMemberPw();
			if(memberPw.equals(checkPw)) {
				isMatched = true;
				resultMap.put("memberInfo", memberInfo);
			}
		}
		resultMap.put("isMatched", isMatched);
		return resultMap;
	}
	
	@Override
	public void removeMember(String memberId) {
		Member memberInfo = memberMapper.getMemberInfoById(memberId);
		String memberLevel = memberInfo.getMemberLevel();
		
		switch (memberLevel) {
		case "mbr_grd_2" -> {
			// 1. 주문 상세
			ordersMapper.removeOrderItemsBySellerId(memberId);
			// 2. 상품
			productsMapper.removeProductsBySellerId(memberId);
			// 3. 거래처
			vendorsMapper.removeVendorsBySellerId(memberId);
			
		}
		case "mbr_grd_3" -> {
			var orderNums = ordersMapper.getOrdersNumById(memberId);
			if(orderNums != null && orderNums.size() >0) {
				// 1. 주문 상세
				ordersMapper.removeOrderItemsByNum(orderNums);
				// 2. 주문
				ordersMapper.removeOrdersById(memberId); 
				}
			}
		}
		// 로그인 이력
		memberMapper.removeMemberLoginLogById(memberId);
		// 회원 탈퇴
		memberMapper.removeMemberById(memberId);
	}
	
	@Override
	public void modifyMemberInfo(Member member) {
		memberMapper.modifyMemberInfo(member);
		
	}
	
	@Override
	public Member getMemberInfoById(String memberId) {
		Member memberInfo = memberMapper.getMemberInfoById(memberId);
		String memberTelNo = memberInfo.getMemberTelNo();
		String[] memberTelNoArr = memberTelNo.split("-");
		
		String memberTelNo1 = memberTelNoArr[0];
		String memberTelNo2 = memberTelNoArr[1];
		String memberTelNo3 = memberTelNoArr[2];
		
		memberInfo.setMemberTelNo1(memberTelNo1);
		memberInfo.setMemberTelNo2(memberTelNo2);
		memberInfo.setMemberTelNo3(memberTelNo3);
		
		return memberInfo;
	}
	
	@Override
	public void addMember(Member member) {
		int result = memberMapper.addMember(member);
		String insertResult = "회원등록 실패";
        if(result > 0) insertResult = "회원등록 성공";
        log.info(insertResult);
	}
	
	@Override
	public List<Member> getMemberList() {
		List<Member> memberList = memberMapper.getMemberList();
		return memberList;
	}
		
}

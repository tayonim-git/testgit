package ksmybatis.admin.orders.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrdersMapper {
	
	// 구매자 주문 이력 삭제
	int removeOrdersById(String customerId);
	
	//  구매 번호로 구매 상세 이력 삭제
	int removeOrderItemsByNum(List<Integer> list);
	
	// 구매자 구매 번호 조회
	List<Integer> getOrdersNumById(String customerId);
	
	// 판매자가 등록한 상품을 구매한 이력 삭제
	int removeOrderItemsBySellerId(String sellerId);

}

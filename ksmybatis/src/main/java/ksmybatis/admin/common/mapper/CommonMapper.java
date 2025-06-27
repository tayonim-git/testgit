package ksmybatis.admin.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmybatis.admin.common.domain.CommonCode;

@Mapper
public interface CommonMapper {
	// 그룹 코드에 일치하는 공통 코드 리스트 조회.
	List<CommonCode> getCommonCodeListByGroupCode(String groupCode);

}

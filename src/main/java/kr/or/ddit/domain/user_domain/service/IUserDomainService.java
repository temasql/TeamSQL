package kr.or.ddit.domain.user_domain.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.domain.user_domain.model.UserDomainVO;

public interface IUserDomainService {
	
	/**
	* Method : insertCommonDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 회원 가입 시 공통 도메인을 사용자 개인 도메인에 생성
	*/
	int insertCommonDomain(String user_id);
	
	
	/**
	* Method : insertUserDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param userDomainVo
	* @return
	* Method 설명 : 도메인 추가
	*/
	int insertUserDomain(UserDomainVO userDomainVo);
	
	
	 /**
	* Method : getName
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 중복된 도메인명 존재여부 체크
	*/
	String getName(Map<String, Object> map);
	
	
	/**
	* Method : findDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 입력한 도메인 id와 일치하는 도메인 타입 검색
	*/
	String findDomain(Map<String, Object> map);
	
	
	/**
	* Method : userDomainList
	* 작성자 : 이영은
	* 변경이력 :
	* @param userDomainVo
	* @return
	* Method 설명 : 사용자 도메인 리스트 조회
	*/
	List<UserDomainVO> userDomainList(UserDomainVO userDomainVo); 
	
	
	/**
	* Method : updateUserDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param userDomainVo
	* @return
	* Method 설명 : 사용자 도메인 수정
	*/
	int updateUserDomain(UserDomainVO userDomainVo);
	
	
	/**
	* Method : deleteUserDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param userDomainVo
	* @return
	* Method 설명 : 사용자 도메인 삭제
	*/
	int deleteUserDomain(UserDomainVO userDomainVo);
	
	
	/**
	* Method : getType
	* 작성자 : 이영은
	* 변경이력 :
	* @param userDomainVo
	* @return
	* Method 설명 : 도메인 타입 조회
	*/
	String getType(UserDomainVO userDomainVo);
	
}

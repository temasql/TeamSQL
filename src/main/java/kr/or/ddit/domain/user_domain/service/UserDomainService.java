package kr.or.ddit.domain.user_domain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.domain.user_domain.dao.IUserDomainDao;
import kr.or.ddit.domain.user_domain.model.UserDomainVO;

@Service
public class UserDomainService implements IUserDomainService{

	@Resource(name = "userDomainDao")
	private IUserDomainDao userDomainDao;

	
	/**
	* Method : insertCommonDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 사용자가 회원가입 시 공통 도메인을 사용자 개인 도메인에 생성
	*/
	@Override
	public int insertCommonDomain(String user_id) {
		return userDomainDao.insertCommonDomain(user_id);
	}

	
	/**
	* Method : insertUserDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param userDomainVo
	* @return
	* Method 설명 : 사용자 도메인 등록
	*/
	@Override
	public int insertUserDomain(UserDomainVO userDomainVo) {
		return userDomainDao.insertUserDomain(userDomainVo);
	}

	
	/**
	* Method : getName
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 도메인 등록 시 도메인명 존재 여부 체크
	*/
	@Override
	public String getName(Map<String, Object> map) {
		return userDomainDao.getName(map);
	}

	
	/**
	* Method : findDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 입력한 도메인 아이디와 일치하는 도메인명 검색
	*/
	@Override
	public String findDomain(Map<String, Object> map) {
		return userDomainDao.findDomain(map);
	}

	
	/**
	* Method : userDomainList
	* 작성자 : 이영은
	* 변경이력 :
	* @param userDomainVo
	* @return
	* Method 설명 : 유저별 도메인 리스트 조회
	*/
	@Override
	public List<UserDomainVO> userDomainList(UserDomainVO userDomainVo) {
		return userDomainDao.userDomainList(userDomainVo);
	}

	
	/**
	* Method : updateUserDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param userDomainVo
	* @return
	* Method 설명 : 유저 도메인 수정
	*/
	@Override
	public int updateUserDomain(UserDomainVO userDomainVo) {
		return userDomainDao.updateUserDomain(userDomainVo);
	}

	
	/**
	* Method : deleteUserDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param userDomainVo
	* @return
	* Method 설명 : 유저 도메인 삭제
	*/
	@Override
	public int deleteUserDomain(UserDomainVO userDomainVo) {
		return userDomainDao.deleteUserDomain(userDomainVo);
	}

	
	/**
	* Method : getType
	* 작성자 : 이영은
	* 변경이력 :
	* @param userDomainVo
	* @return
	* Method 설명 : 도메인명과 일치하는 도메인 타입 리턴
	*/
	@Override
	public String getType(UserDomainVO userDomainVo) {
		return userDomainDao.getType(userDomainVo);
	}


}

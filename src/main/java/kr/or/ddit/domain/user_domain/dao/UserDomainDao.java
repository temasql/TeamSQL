package kr.or.ddit.domain.user_domain.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.domain.user_domain.model.UserDomainVO;

@Repository
public class UserDomainDao implements IUserDomainDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	* Method : insertCommonDomain
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 사용자가 회원가입할때 공통 도메인을  사용자 개인 도메인에 생성하는 쿼리
	*/
	@Override
	public int insertCommonDomain(String user_id) {
		return sqlSession.insert("userDomain.insertCommonDomain", user_id);
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
		return sqlSession.insert("userDomain.insertUserDomain", userDomainVo);
	}

	
	/**
	* Method : getName
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 도메인 등록시 해당 도메인명 존재하는지 검색
	*/
	@Override
	public String getName(Map<String, Object> map) {
		return sqlSession.selectOne("userDomain.getName", map);
	}

	
	/**
	* Method : findDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 유저가 검색한 도메인명과 일치하는 도메인 타입을 리턴
	*/
	@Override
	public String findDomain(Map<String, Object> map) {
		return sqlSession.selectOne("userDomain.findDomain", map);
	}

	
	/**
	* Method : userDomainList
	* 작성자 : 이영은
	* 변경이력 :
	* @param userDomainVo
	* @return
	* Method 설명 : 각 유저별 도메인 리스트 조회
	*/
	@Override
	public List<UserDomainVO> userDomainList(UserDomainVO userDomainVo) {
		return sqlSession.selectList("userDomain.userDomainList", userDomainVo);
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
		return sqlSession.update("userDomain.updateUserDomain", userDomainVo);
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
		return sqlSession.delete("userDomain.deleteUserDomain", userDomainVo);
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
		return sqlSession.selectOne("userDomain.getType", userDomainVo);
	}

}

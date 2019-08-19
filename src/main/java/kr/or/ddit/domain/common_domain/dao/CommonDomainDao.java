package kr.or.ddit.domain.common_domain.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.domain.common_domain.model.CommonDomainVO;

@Repository
public class CommonDomainDao implements ICommonDomainDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	
	/**
	* Method : allDomainList
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 전체 도메인 리스트 조회
	*/
	@Override
	public List<CommonDomainVO> allDomainList() {
		return sqlSession.selectList("commonDomain.allDomainList");
	}

	
	/**
	* Method : domainCnt
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 도메인 갯수
	*/
	@Override
	public int domainCnt() {
		return sqlSession.selectOne("commonDomain.domainCnt");
	}

	
	/**
	* Method : domainPagingList
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 도메인 페이징 리스트
	*/
	@Override
	public List<CommonDomainVO> domainPagingList(Map<String, Object> map) {
		return sqlSession.selectList("commonDomain.domainPagingList", map);
	}


	/**
	* Method : addDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param domainVo
	* @return
	* Method 설명 : 공통 도메인 추가
	*/
	@Override
	public int addDomain(CommonDomainVO domainVo) {
		return sqlSession.insert("commonDomain.addDomain", domainVo);
	}


	/**
	* Method : modifyDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param domainVo
	* @return
	* Method 설명 : 공통 도메인 수정
	*/
	@Override
	public int modifyDomain(CommonDomainVO domainVo) {
		return sqlSession.update("commonDomain.modifyDomain", domainVo);
	}


	/**
	* Method : deleteDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param cdomain_id
	* @return
	* Method 설명 : 공통 도메인 삭제
	*/
	@Override
	public int deleteDomain(int cdomain_id) {
		return sqlSession.delete("commonDomain.deleteDomain", cdomain_id);
	}

	
}

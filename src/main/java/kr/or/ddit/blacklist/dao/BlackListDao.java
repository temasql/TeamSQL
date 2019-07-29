package kr.or.ddit.blacklist.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.blacklist.model.BlackListVO;

@Repository
public class BlackListDao implements IBlackListDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	* Method : insertBlackList
	* 작성자 : 이중석
	* 변경이력 :
	* @param blackListVo
	* @return
	* Method 설명 : 블랙리스트 등록
	*/
	@Override
	public int insertBlackList(BlackListVO blackListVo) {
		return sqlSession.insert("blackList.insertBlackList", blackListVo);
	}
	
	/**
	* Method : blackList
	* 작성자 : 이중석
	* 변경이력 :
	* @param pageMap
	* @return
	* Method 설명 : 블랙리스트 페이징 처리
	*/
	@Override
	public List<BlackListVO> blackList(Map<String, Object> pageMap) {
		return sqlSession.selectList("blackList.blackList", pageMap);
	}


	/**
	* Method : blackListSearchCount
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id_fk
	* @return
	* Method 설명 : 블랙리스트 수
	*/
	@Override
	public int blackListSearchCount(String search) {
		return sqlSession.selectOne("blackList.blackListSearchCount", search);
	}

	/**
	* Method : deleteBlackListMG
	* 작성자 : 이중석
	* 변경이력 :
	* @param userMap
	* @return
	* Method 설명 : 블랙리스트 해제
	*/
	@Override
	public int deleteBlackListMG(Map<String, Object> userMap) {
		return sqlSession.update("blackList.deleteBlackListMG", userMap);
	}

	

}

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

	@Override
	public int insertBlackList(BlackListVO blackListVo) {
		return sqlSession.insert("blackList.insertBlackList", blackListVo);
	}
	
	@Override
	public BlackListVO get(String id) {
		return sqlSession.selectOne("blackList.get", id);
	}
	
	@Override
	public List<BlackListVO> blackList(Map<String, Object> pageMap) {
		return sqlSession.selectList("blackList.blackList", pageMap);
	}

	@Override
	public List<BlackListVO> map(Map<String, Object> map) {
		return sqlSession.selectList("blackList.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("blackList.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("blackList.delete", id);
	}

	/**
	* Method : blackListSearchCount
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id_fk
	* @return
	* Method 설명 :
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
	* Method 설명 :
	*/
	@Override
	public int deleteBlackListMG(Map<String, Object> userMap) {
		return sqlSession.update("blackList.deleteBlackListMG", userMap);
	}

	

}

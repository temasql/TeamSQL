package kr.or.ddit.history.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.history.model.HistoryVO;

@Repository
public class HistoryDao implements IHistoryDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(HistoryVO historyVo) {
		return sqlSession.insert("history.insert", historyVo);
	}
	
	/**
	 * 
	* Method : accountList
	* 작성자 : PC20
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : DB계정명 리스트 조회
	 */
	@Override
	public List<String> accountList(String user_id) {
		return sqlSession.selectList("history.accountList",user_id);
	}
	
	/**
	 * 
	* Method : accountAndChangedList
	* 작성자 : PC20
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB 계정명 / 변경일자 리스트 조회
	 */
	@Override
	public List<HistoryVO> changedList(String account_id) {
		return sqlSession.selectList("history.changedList",account_id);
	}
	

	@Override
	public List<HistoryVO> map(Map<String, Object> map) {
		return sqlSession.selectList("history.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("history.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("history.delete", id);
	}


}

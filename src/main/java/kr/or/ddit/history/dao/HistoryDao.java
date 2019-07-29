package kr.or.ddit.history.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.history.model.HistoryVO;
import kr.or.ddit.page.model.PageVo;

@Repository
public class HistoryDao implements IHistoryDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	 * 
	* Method : accountAndChangedList
	* 작성자 : 강호길
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB 계정명 / 변경일자 리스트 조회
	 */
	@Override
	public List<HistoryVO> changedList(String user_id) {
		return sqlSession.selectList("history.changedList",user_id);
	}
	
	/**
	 * 
	* Method : changedPagList
	* 작성자 : 강호길
	* 변경이력 :
	* @param pageMap
	* @return
	* Method 설명 : DB 계정명 / 변경일자 페이징 리스트 조회
	 */
	@Override
	public List<HistoryVO> changedPagingList(Map<String, Object> pageMap) {
		return sqlSession.selectList("history.changedPagingList",pageMap);
	}
	
	/**
	 * 
	* Method : changedDetailList
	* 작성자 : 강호길
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : DB변경 이력 상세 리스트 조회
	 */
	@Override
	public List<HistoryVO> changedDetailList(String object_owner) {
		return sqlSession.selectList("history.changedDetailList", object_owner);
	}
	
	/**
	 * 
	* Method : changedPagingList
	* 작성자 : 강호길
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : DB변경 이력 상세 페이징 리스트 조회
	 */
	@Override
	public List<HistoryVO> changedDetailPagingList(Map<String, Object> pageMap) {
		return sqlSession.selectList("history.changedDetailPagingList",pageMap);
	}

	
	/**
	 * 
	* Method : accountCnt
	* 작성자 : 강호길
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : DB변경 계정 전체수 조회
	 */
	@Override
	public int accountCnt(String user_id) {
		return sqlSession.selectOne("history.accountCnt",user_id);
	}

	/**
	 * 
	* Method : historyCnt
	* 작성자 : 강호길
	* 변경이력 :
	* @param object_owner
	* @return
	* Method 설명 : DB 변경 이력 전체수 조회
	 */
	@Override
	public int historyCnt(String object_owner) {
		return sqlSession.selectOne("history.historyCnt",object_owner);
	}

}

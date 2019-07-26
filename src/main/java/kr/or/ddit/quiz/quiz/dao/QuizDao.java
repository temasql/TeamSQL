package kr.or.ddit.quiz.quiz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.quiz.quiz.model.QuizVO;

@Repository
public class QuizDao implements IQuizDao{
	private static final Logger logger = LoggerFactory.getLogger(QuizDao.class);

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	* Method : insert
	* 작성자 : PC19
	* 변경이력 :
	* @param quizVo
	* @return
	* Method 설명 : 관리자가 퀴즈별로 문제를 등록 하는 메서드
	*/
	@Override
	public int insert(QuizVO quizVo) {
		return sqlSession.insert("quiz.insert", quizVo);
	}
	
	@Override
	public QuizVO get(String id) {
		return sqlSession.selectOne("quiz.get", id);
	}
	
	/**
	* Method : list
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 선택한 퀴즈의 리스트를 반환하는 메서드
	*/
	@Override
	public List<QuizVO> quizList(String quiz_right, PageVo pageVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quiz_right", quiz_right);
		map.put("page", pageVO.getPage());
		map.put("pageSize", pageVO.getPageSize());
		
		return sqlSession.selectList("quiz.quizList", map);
	}
	
	/**
	* Method : quizListCnt
	* 작성자 : PC19
	* 변경이력 :
	* @param quiz_right
	* @return
	* Method 설명 : 퀴즈별 리스트 갯수를 반환하는 메서드
	*/
	@Override
	public int quizListCnt(String quiz_right) {
		logger.debug("quiz_right : {}", quiz_right);
		return sqlSession.selectOne("quiz.quizListCnt", quiz_right);
	}

	@Override
	public List<QuizVO> map(Map<String, Object> map) {
		return sqlSession.selectList("quiz.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("quiz.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("quiz.delete", id);
	}

}

package kr.or.ddit.quiz.quiz_answer.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.quiz.quiz_answer.model.QuizAnswerVO;

//@Repository
public class QuizAnswerDao implements IQuizAnswerDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(QuizAnswerVO quizAnswerVo) {
		return sqlSession.insert("quizAnswer.insert", quizAnswerVo);
	}
	
	@Override
	public QuizAnswerVO get(String id) {
		return sqlSession.selectOne("quizAnswer.get", id);
	}
	
	@Override
	public List<QuizAnswerVO> list() {
		return sqlSession.selectList("quizAnswer.list");
	}

	@Override
	public List<QuizAnswerVO> map(Map<String, Object> map) {
		return sqlSession.selectList("quizAnswer.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("quizAnswer.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("quizAnswer.delete", id);
	}

	

}

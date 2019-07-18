package kr.or.ddit.quiz.quiz.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.quiz.quiz.model.QuizVO;

//@Repository
public class QuizDao implements IQuizDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(QuizVO quizVo) {
		return sqlSession.insert("quiz.insert", quizVo);
	}
	
	@Override
	public QuizVO get(String id) {
		return sqlSession.selectOne("quiz.get", id);
	}
	
	@Override
	public List<QuizVO> list() {
		return sqlSession.selectList("quiz.list");
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

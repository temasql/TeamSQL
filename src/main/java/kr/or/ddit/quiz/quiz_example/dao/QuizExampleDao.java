package kr.or.ddit.quiz.quiz_example.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.quiz.quiz_example.model.QuizExampleVO;

//@Repository
public class QuizExampleDao implements IQuizExampleDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(QuizExampleVO quizExampleVo) {
		return sqlSession.insert("quizExample.insert", quizExampleVo);
	}
	
	@Override
	public QuizExampleVO get(String id) {
		return sqlSession.selectOne("quizExample.get", id);
	}
	
	@Override
	public List<QuizExampleVO> list() {
		return sqlSession.selectList("quizExample.list");
	}

	@Override
	public List<QuizExampleVO> map(Map<String, Object> map) {
		return sqlSession.selectList("quizExample.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("quizExample.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("quizExample.delete", id);
	}

	

}

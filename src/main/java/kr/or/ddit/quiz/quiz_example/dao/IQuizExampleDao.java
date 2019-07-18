package kr.or.ddit.quiz.quiz_example.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.quiz.quiz_example.model.QuizExampleVO;

public interface IQuizExampleDao {

	int insert(QuizExampleVO quizExampleVo);
	
	QuizExampleVO get(String id);
	
	List<QuizExampleVO> list();
	
	List<QuizExampleVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}

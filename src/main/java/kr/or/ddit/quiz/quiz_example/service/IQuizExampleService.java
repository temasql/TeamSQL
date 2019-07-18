package kr.or.ddit.quiz.quiz_example.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.quiz.quiz_example.model.QuizExampleVO;

public interface IQuizExampleService {
	
	int insert(QuizExampleVO quizExampleVo);
	
	QuizExampleVO get(String id);
	
	List<QuizExampleVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}

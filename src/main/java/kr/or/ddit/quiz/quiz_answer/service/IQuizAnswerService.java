package kr.or.ddit.quiz.quiz_answer.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.quiz.quiz_answer.model.QuizAnswerVO;

public interface IQuizAnswerService {
	
	int insert(QuizAnswerVO quizAnswerVo);
	
	QuizAnswerVO get(String id);
	
	List<QuizAnswerVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}

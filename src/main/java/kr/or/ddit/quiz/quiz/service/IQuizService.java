package kr.or.ddit.quiz.quiz.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.quiz.quiz.model.QuizVO;

public interface IQuizService {
	
	int insert(QuizVO quizVo);
	
	QuizVO get(String id);
	
	List<QuizVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}

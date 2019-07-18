package kr.or.ddit.quiz.quiz_answer.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.quiz.quiz_answer.model.QuizAnswerVO;

public interface IQuizAnswerDao {

	int insert(QuizAnswerVO quizAnswerVo);
	
	QuizAnswerVO get(String id);
	
	List<QuizAnswerVO> list();
	
	List<QuizAnswerVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}

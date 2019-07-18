package kr.or.ddit.quiz.quiz.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.quiz.quiz.model.QuizVO;

public interface IQuizDao {

	int insert(QuizVO quizVo);
	
	QuizVO get(String id);
	
	List<QuizVO> list();
	
	List<QuizVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}

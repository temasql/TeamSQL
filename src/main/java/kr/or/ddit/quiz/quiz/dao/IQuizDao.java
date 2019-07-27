package kr.or.ddit.quiz.quiz.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.quiz.quiz.model.QuizVO;
import kr.or.ddit.quiz.quiz_answer.model.QuizAnswerVO;

public interface IQuizDao {

	int insert_question(QuizVO quizVo);
	
	int maxQuiz_Id();
	
	int insert_answer(QuizAnswerVO quizAnswerVO);
	
	QuizVO get(String id);
	
	List<QuizVO> quizList(String quiz_right, PageVo pageVO);
	
	int quizListCnt(String quiz_right);
	
	List<QuizVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}

package kr.or.ddit.quiz.quiz.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.quiz.quiz.model.QuizAndAnswerVO;
import kr.or.ddit.quiz.quiz.model.QuizVO;
import kr.or.ddit.quiz.quiz_answer.model.QuizAnswerVO;

public interface IQuizService {
	
	int insert_question(QuizVO quizVO, QuizAnswerVO quizAnswerVO);
	
	QuizVO get(String id);
	
	List<QuizVO> quizList(String quiz_right, PageVo pageVO);
	
	int quizListCnt(String quiz_right);
	
	Map<String, Object> map(Map<String, Object> map);
	
	int deleteQuiz(int quiz_id);
	
	QuizAndAnswerVO readQuiz(QuizVO quizVO);
	
	int updateQuiz(QuizAndAnswerVO quizAndAnswerVO);
	
}

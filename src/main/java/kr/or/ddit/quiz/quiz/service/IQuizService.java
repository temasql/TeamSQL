package kr.or.ddit.quiz.quiz.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.quiz.quiz.model.QuizAndAnswerVO;
import kr.or.ddit.quiz.quiz.model.QuizVO;
import kr.or.ddit.quiz.quiz_answer.model.QuizAnswerVO;
import kr.or.ddit.quiz.quiz_example.model.QuizExampleVO;

public interface IQuizService {
	
	int insert_question(QuizVO quizVO, QuizAnswerVO quizAnswerVO);
	
	int insertEssay(QuizVO quizVO, QuizAnswerVO quizAnswerVO, String[] answerArr);
	
	QuizVO get(String id);
	
	List<QuizVO> quizList(String quiz_right, PageVo pageVO);
	
	Map<String, Object> quizAnswer(QuizVO quizVO);
	
	int quizListCnt(String quiz_right);
	
	Map<String, Object> map(Map<String, Object> map);
	
	int deleteQuiz(int quiz_id);
	
	QuizAndAnswerVO readQuiz(QuizVO quizVO);
	
	int updateQuiz(QuizAndAnswerVO quizAndAnswerVO);
	
	int updateMultipleQuiz(QuizAndAnswerVO quizAndAnswerVO, QuizExampleVO quizExampleVO, String[] example_content);
	
	int insertMultipleQuiz(QuizVO quizVO, QuizAnswerVO quizAnswerVO, QuizExampleVO quizExampleVO, String[] example_content);
	
	Map<String, Object> multipleQuizList(QuizVO quizVO);
	
	QuizAndAnswerVO userReadQuiz(String quiz_right);
	
	QuizAndAnswerVO userNextQuiz(QuizVO quizVO);
	
	List<QuizExampleVO> userMultipleList(QuizAndAnswerVO quizAndAnswerVO);
	
	Map<String, Object> userAnswerList(QuizVO quizVO);
}

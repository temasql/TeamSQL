package kr.or.ddit.quiz.quiz.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.quiz.quiz.model.QuizAndAnswerVO;
import kr.or.ddit.quiz.quiz.model.QuizVO;
import kr.or.ddit.quiz.quiz_answer.model.QuizAnswerVO;
import kr.or.ddit.quiz.quiz_example.model.QuizExampleVO;
import kr.or.ddit.user.model.UserVO;

public interface IQuizDao {

	int insert_question(QuizVO quizVo);
	
	int maxQuiz_Id();
	
	int insert_answer(QuizAnswerVO quizAnswerVO);
	
	QuizVO get(String id);
	
	List<QuizVO> quizList(String quiz_right, PageVo pageVO);
	
	List<QuizAnswerVO> quizAnswerList(QuizVO quizVO);
	
	int quizListCnt(String quiz_right);
	
	List<QuizVO> map(Map<String, Object> map);
	
	int deleteQuiz(int quiz_id);
	
	QuizAndAnswerVO readQuiz(QuizVO quizVO);
	
	QuizVO readEssay(QuizVO quizVO);
	
	int updateQuiz(QuizAndAnswerVO quizAndAnswerVO);
	
	int updateQuizAnswer(QuizAndAnswerVO quizAndAnswerVO);
	
	int updateQuizExample(QuizExampleVO quizExampleVO);
	
	int insertMultipleQuizExample(QuizExampleVO quizExampleVO);
	
	List<QuizExampleVO> multipleQuizList(QuizAndAnswerVO quizAndAnswerVO);
	
	QuizAndAnswerVO userReadQuiz(String quiz_right);
	
	QuizAndAnswerVO userNextQuiz(QuizVO quizVO);
	
	List<QuizAndAnswerVO> userReadQuizList(String quiz_right);
	
	List<Map> answerCompare(String quiz_answer);
}

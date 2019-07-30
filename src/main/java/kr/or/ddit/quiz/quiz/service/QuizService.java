package kr.or.ddit.quiz.quiz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.quiz.quiz.dao.IQuizDao;
import kr.or.ddit.quiz.quiz.model.QuizAndAnswerVO;
import kr.or.ddit.quiz.quiz.model.QuizVO;
import kr.or.ddit.quiz.quiz_answer.model.QuizAnswerVO;

@Service
public class QuizService implements IQuizService{
	private static final Logger logger = LoggerFactory.getLogger(QuizService.class);

	@Resource(name = "quizDao")
	private IQuizDao quizDao;

	/**
	* Method : insert_question
	* 작성자 : PC19
	* 변경이력 :
	* @param quizVO
	* @param quizAnswerVO
	* @return
	* Method 설명 : 관리자가 문제와 해답을 등록 하는 메서드
	*/
	@Override
	public int insert_question(QuizVO quizVO, QuizAnswerVO quizAnswerVO) {
		int questionResult = quizDao.insert_question(quizVO);
		
		int answerResult = quizDao.insert_answer(quizAnswerVO);
		
		return questionResult+answerResult;
	}
	
	@Override
	public QuizVO get(String id) {
		return quizDao.get(id);
	}
	
	@Override
	public List<QuizVO> quizList(String quiz_right, PageVo pageVO) {
		return quizDao.quizList(quiz_right, pageVO);
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<QuizVO> mapList =  quizDao.map(map);
		return resultMap;
	}
	
	/**
	* Method : quizListCnt
	* 작성자 : PC19
	* 변경이력 :
	* @param quiz_right
	* @return
	* Method 설명 : 퀴즈별 리스트 갯수를 반환하는 메서드
	*/
	@Override
	public int quizListCnt(String quiz_right) {
		return quizDao.quizListCnt(quiz_right);
	}
	
	/**
	* Method : deleteQuiz
	* 작성자 : PC19
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 선택한 퀴즈의 시퀀스 값을 받아서 해당 퀴즈 삭제 메서드
	*/
	@Override
	public int deleteQuiz(int quiz_id) {
		return quizDao.deleteQuiz(quiz_id);
	}

	/**
	* Method : readQuiz
	* 작성자 : PC19
	* 변경이력 :
	* @param quiz_id
	* @return
	* Method 설명 : 선택한 퀴즈의 정보를 반환하는 메서드
	*/
	@Override
	public QuizAndAnswerVO readQuiz(QuizVO quizVO) {
		return quizDao.readQuiz(quizVO);
	}

	/**
	* Method : updateQuiz
	* 작성자 : PC19
	* 변경이력 :
	* @param quizAndAnswerVO
	* @return
	* Method 설명 : 선택한 퀴즈의 문제와 정답 해설을 수정하는 메서드
	*/
	@Override
	public int updateQuiz(QuizAndAnswerVO quizAndAnswerVO) {
		int quizResult = quizDao.updateQuiz(quizAndAnswerVO);
		int quizAnswerResult = quizDao.updateQuizAnswer(quizAndAnswerVO);
		
		return quizResult + quizAnswerResult; 
	}
}

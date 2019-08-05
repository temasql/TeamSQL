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
import kr.or.ddit.quiz.quiz_example.model.QuizExampleVO;

@Service
public class QuizService implements IQuizService{
	private static final Logger logger = LoggerFactory.getLogger(QuizService.class);

	@Resource(name = "quizDao")
	private IQuizDao quizDao;

	/**
	* Method : insert_question
	* 작성자 : 손주형
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
	

	/**
	* Method : insertEssay
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizVO
	* @param quizAnswerVO
	* @return
	* Method 설명 : 관리자가 주관식 퀴즈를 등록하는 메서드
	*/
	@Override
	public int insertEssay(QuizVO quizVO, QuizAnswerVO quizAnswerVO, String[] answerArr) {
		int questionResult = quizDao.insert_question(quizVO);
		
		int answerResult = 0; 
			for(int i=0; i<answerArr.length; i++) {
				if(!answerArr[i].equals("")) {
					quizAnswerVO.setQuiz_answer(answerArr[i]);
					
					answerResult += quizDao.insert_answer(quizAnswerVO);
				}
			}
		
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
	* 작성자 : 손주형
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
	* 작성자 : 손주형
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
	* 작성자 : 손주형
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
	* 작성자 : 손주형
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
	
	/**
	* Method : updateMultipleQuiz
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizAndAnswerVO
	* @return
	* Method 설명 : 객관식 문제, 정답, 해설, 보기문제를 수정하는 메서드, 최종 return값 : 7
	*/
	@Override
	public int updateMultipleQuiz(QuizAndAnswerVO quizAndAnswerVO, QuizExampleVO quizExampleVO, String[] example_content) {
		int resultQuiz = quizDao.updateQuiz(quizAndAnswerVO);
		int resultQuizAnswer = quizDao.updateQuizAnswer(quizAndAnswerVO);
		
		quizExampleVO.setQuiz_id_fk(quizAndAnswerVO.getQuiz_id());
		
		int resultQuizExample=0;
		for(int i=0; i<example_content.length; i++) {
			quizExampleVO.setExample_num(i+1);
			quizExampleVO.setExample_content(example_content[i]);
			
			resultQuizExample += quizDao.updateQuizExample(quizExampleVO);
		}
		return resultQuiz + resultQuizAnswer + resultQuizExample;
	}

	/**
	* Method : insertMultipleQuiz
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizVO
	* @param quizAnswerVO
	* @param quizExampleVO
	* @return
	* Method 설명 : 객관식 퀴즈 등록 메서드, 성공 했을 때 리턴 = 7
	*/
	@Override
	public int insertMultipleQuiz(QuizVO quizVO, QuizAnswerVO quizAnswerVO, QuizExampleVO quizExampleVO, String[] example_content) {
		int quiz = quizDao.insert_question(quizVO);
		int answer = quizDao.insert_answer(quizAnswerVO);
		int example = 0;
		
		for(int i=0; i<example_content.length; i++) {
			quizExampleVO.setExample_num(i+1);
			quizExampleVO.setExample_content(example_content[i]);
			example += quizDao.insertMultipleQuizExample(quizExampleVO);
		}
		
		
		return quiz+answer+example;
	}

	/**
	* Method : multipleQuizList
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizVO
	* @return
	* Method 설명 : 퀴즈 아이디에 해당하는 문제, 정답, 해설, 퀴즈보기와 퀴즈 보기 내용을 리스트로 반환한다.
	*/
	@Override
	public Map<String, Object> multipleQuizList(QuizVO quizVO) {
		logger.debug("서비스 : {}", quizVO);
		QuizAndAnswerVO quizAndAnswerVO = quizDao.readQuiz(quizVO);
		logger.debug("서비스 quizAndAnswerVO : {}", quizAndAnswerVO);
		List<QuizExampleVO> exampleList = quizDao.multipleQuizList(quizAndAnswerVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quizAndAnswerVO", quizAndAnswerVO);
		map.put("exampleList", exampleList);
		
		return map;
	}


	/**
	* Method : quizAnswer
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizVO
	* @return
	* Method 설명 : 주관식 문제, 답, 해설을 조회하는 메서드
	*/
	@Override
	public Map<String, Object> quizAnswer(QuizVO quizVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		QuizVO quizVO_1 = quizDao.readEssay(quizVO);
		List<QuizAnswerVO> quizAnswerList = quizDao.quizAnswerList(quizVO);
		
		map.put("quizVO", quizVO_1);
		map.put("quizAnswerList", quizAnswerList);
		
		return map;
	}
}

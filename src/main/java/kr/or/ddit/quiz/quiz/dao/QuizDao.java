package kr.or.ddit.quiz.quiz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.quiz.quiz.model.QuizAndAnswerVO;
import kr.or.ddit.quiz.quiz.model.QuizVO;
import kr.or.ddit.quiz.quiz_answer.model.QuizAnswerVO;
import kr.or.ddit.quiz.quiz_example.model.QuizExampleVO;

@Repository
public class QuizDao implements IQuizDao{
	private static final Logger logger = LoggerFactory.getLogger(QuizDao.class);

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	* Method : insert
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizVo
	* @return
	* Method 설명 : 관리자가 퀴즈별로 문제를 등록 하는 메서드
	*/
	@Override
	public int insert_question(QuizVO quizVo) {
		return sqlSession.insert("quiz.insertQuestion", quizVo);
	}
	
	@Override
	public int maxQuiz_Id() {
		return sqlSession.selectOne("quiz.maxQuiz_Id");
	}
	
	/**
	* Method : insert_answer
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizAnswerVO
	* @return
	* Method 설명 : 관리자가 퀴즈별로 문제에 대한 정답과 해답을 등록 하는 메서드
	*/
	@Override
	public int insert_answer(QuizAnswerVO quizAnswerVO) {
		return sqlSession.insert("quiz.insertAnswer", quizAnswerVO);
	}
	
	@Override
	public QuizVO get(String id) {
		return sqlSession.selectOne("quiz.get", id);
	}
	
	/**
	* Method : list
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : 선택한 퀴즈의 리스트를 반환하는 메서드
	*/
	@Override
	public List<QuizVO> quizList(String quiz_right, PageVo pageVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quiz_right", quiz_right);
		map.put("page", pageVO.getPage());
		map.put("pageSize", pageVO.getPageSize());
		
		return sqlSession.selectList("quiz.quizList", map);
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
		logger.debug("quiz_right : {}", quiz_right);
		return sqlSession.selectOne("quiz.quizListCnt", quiz_right);
	}

	@Override
	public List<QuizVO> map(Map<String, Object> map) {
		return sqlSession.selectList("quiz.map", map);
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
		return sqlSession.delete("quiz.deleteQuiz", quiz_id);
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
		return sqlSession.selectOne("quiz.readQuiz", quizVO);
	}
	
	/**
	* Method : readEssay
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizVO
	* @return
	* Method 설명 : 주관식 문제를 조회하는 메서드
	*/
	@Override
	public QuizVO readEssay(QuizVO quizVO) {
		return sqlSession.selectOne("quiz.quizEssay", quizVO);
	}

	/**
	* Method : updateQuiz
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizAndAnswerVO
	* @return
	* Method 설명 : 선택한 퀴즈의 문제를 수정하는 메서드
	*/
	@Override
	public int updateQuiz(QuizAndAnswerVO quizAndAnswerVO) {
		return sqlSession.update("quiz.updateQuiz", quizAndAnswerVO);
	}

	/**
	* Method : updateQuizAnswer
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizAndAnswerVO
	* @return
	* Method 설명 : 선택한 퀴즈의 정답과 해설을 수정하는 메서드
	*/
	@Override
	public int updateQuizAnswer(QuizAndAnswerVO quizAndAnswerVO) {
		return sqlSession.update("quiz.updateQuizAnswer", quizAndAnswerVO);
	}

	/**
	* Method : updateQuizExample
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizExampleVO
	* @return
	* Method 설명 : 선택한 퀴즈의 보기를 수정하는 메서드
	*/
	@Override
	public int updateQuizExample(QuizExampleVO quizExampleVO) {
		return sqlSession.update("quiz.updateQuizExample", quizExampleVO);
	}
	
	/**
	* Method : insertMultipleQuizExample
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizExampleVO
	* @return
	* Method 설명 : 객관식 퀴즈 보기, 퀴즈보기에 해당하는 내용을 등록하는 메서드 
	*/
	@Override
	public int insertMultipleQuizExample(QuizExampleVO quizExampleVO) {
		return sqlSession.insert("quiz.insertMultipleQuizExample", quizExampleVO);
	}

	/**
	* Method : multipleQuizList
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizVO
	* @return
	* Method 설명 : 퀴즈 아이디에 해당하는 퀴즈보기와 퀴즈 보기 내용을 리스트로 반환한다.
	*/
	@Override
	public List<QuizExampleVO> multipleQuizList(QuizAndAnswerVO quizAndAnswerVO) {
		return sqlSession.selectList("quiz.multipleQuizList", quizAndAnswerVO);
	}

	/**
	* Method : quizAnswerList
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizVO
	* @return
	* Method 설명 : 주관식 답변을 조회할 때의 메서드
	*/
	@Override
	public List<QuizAnswerVO> quizAnswerList(QuizVO quizVO) {
		return sqlSession.selectList("quiz.quizAnswerList", quizVO);
	}

	/**
	* Method : userReadQuiz
	* 작성자 : 손주형
	* 변경이력 :
	* @param quiz_right
	* @return
	* Method 설명 : 유저 각 퀴즈 조회 메서드
	*/
	@Override
	public QuizAndAnswerVO userReadQuiz(String quiz_right) {
		return sqlSession.selectOne("quiz.userReadQuiz", quiz_right);
	}
	
	

	/**
	* Method : userNextQuiz
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizAndAnswerVO
	* @return
	* Method 설명 : 유저가 문제를 풀었을때 다음문제 조회하는 메서드(주관식 제외)
	*/
	@Override
	public QuizAndAnswerVO userNextQuiz(QuizVO quizVO) {
		return sqlSession.selectOne("quiz.userNextQuiz", quizVO);
	}

	/**
	* Method : userReadQuizList
	* 작성자 : 손주형
	* 변경이력 :
	* @param quiz_right
	* @return
	* Method 설명 : 유저가 주관식 문제를 풀었을때 다음문제를 조회하는 메서드
	*/
	@Override
	public List<QuizAndAnswerVO> userReadQuizList(String quiz_right) {
		return sqlSession.selectList("quiz.userReadQuiz", quiz_right);
	}
}

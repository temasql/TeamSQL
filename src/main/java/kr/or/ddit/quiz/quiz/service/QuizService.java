package kr.or.ddit.quiz.quiz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.quiz.quiz.dao.IQuizDao;
import kr.or.ddit.quiz.quiz.model.QuizVO;

@Service
public class QuizService implements IQuizService{

	@Resource(name = "quizDao")
	private IQuizDao quizDao;

	@Override
	public int insert(QuizVO quizVo) {
		return quizDao.insert(quizVo);
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

	@Override
	public int update(String id) {
		return quizDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return quizDao.delete(id);
	}

}

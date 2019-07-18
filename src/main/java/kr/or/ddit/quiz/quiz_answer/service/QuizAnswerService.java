package kr.or.ddit.quiz.quiz_answer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.quiz.quiz_answer.dao.IQuizAnswerDao;
import kr.or.ddit.quiz.quiz_answer.model.QuizAnswerVO;

//@Service
public class QuizAnswerService implements IQuizAnswerService{

	@Resource(name = "quizAnswerDao")
	private IQuizAnswerDao quizAnswerdao;

	@Override
	public int insert(QuizAnswerVO quizAnswerVo) {
		return quizAnswerdao.insert(quizAnswerVo);
	}
	
	@Override
	public QuizAnswerVO get(String id) {
		return quizAnswerdao.get(id);
	}
	
	@Override
	public List<QuizAnswerVO> list() {
		return quizAnswerdao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<QuizAnswerVO> mapList =  quizAnswerdao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return quizAnswerdao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return quizAnswerdao.delete(id);
	}

}

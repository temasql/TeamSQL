package kr.or.ddit.quiz.quiz.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.quiz.quiz.dao.IQuizDao;
import kr.or.ddit.quiz.quiz.model.QuizVO;

//@Service
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
	public List<QuizVO> list() {
		return quizDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<QuizVO> mapList =  quizDao.map(map);
		return resultMap;
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

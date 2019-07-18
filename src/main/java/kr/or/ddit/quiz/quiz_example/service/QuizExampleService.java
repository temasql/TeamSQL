package kr.or.ddit.quiz.quiz_example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.quiz.quiz_example.dao.IQuizExampleDao;
import kr.or.ddit.quiz.quiz_example.model.QuizExampleVO;

//@Service
public class QuizExampleService implements IQuizExampleService{

	@Resource(name = "quizExampleDao")
	private IQuizExampleDao quizExampleDao;

	@Override
	public int insert(QuizExampleVO quizExampleVo) {
		return quizExampleDao.insert(quizExampleVo);
	}
	
	@Override
	public QuizExampleVO get(String id) {
		return quizExampleDao.get(id);
	}
	
	@Override
	public List<QuizExampleVO> list() {
		return quizExampleDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<QuizExampleVO> mapList =  quizExampleDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return quizExampleDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return quizExampleDao.delete(id);
	}

}

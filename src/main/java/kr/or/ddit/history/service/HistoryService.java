package kr.or.ddit.history.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.history.dao.IHistoryDao;
import kr.or.ddit.history.model.HistoryVO;

//@Service
public class HistoryService implements IHistoryService{

	@Resource(name = "historyDao")
	private IHistoryDao historyDao;

	@Override
	public int insert(HistoryVO historyVo) {
		return historyDao.insert(historyVo);
	}
	
	@Override
	public HistoryVO get(String id) {
		return historyDao.get(id);
	}
	
	@Override
	public List<HistoryVO> list() {
		return historyDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<HistoryVO> mapList =  historyDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return historyDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return historyDao.delete(id);
	}

}

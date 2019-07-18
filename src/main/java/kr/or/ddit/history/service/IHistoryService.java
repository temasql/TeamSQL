package kr.or.ddit.history.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.history.model.HistoryVO;

public interface IHistoryService {
	
	int insert(HistoryVO historyVo);
	
	HistoryVO get(String id);
	
	List<HistoryVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}

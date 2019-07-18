package kr.or.ddit.history.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.history.model.HistoryVO;

public interface IHistoryDao {

	int insert(HistoryVO historyVo);
	
	HistoryVO get(String id);
	
	List<HistoryVO> list();
	
	List<HistoryVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}

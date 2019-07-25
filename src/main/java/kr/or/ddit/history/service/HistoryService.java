package kr.or.ddit.history.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.history.dao.IHistoryDao;
import kr.or.ddit.history.model.HistoryVO;

@Service
public class HistoryService implements IHistoryService{

	@Resource(name = "historyDao")
	private IHistoryDao historyDao;

	@Override
	public int insert(HistoryVO historyVo) {
		return historyDao.insert(historyVo);
	}
	
	/**
	 * 
	* Method : accountList
	* 작성자 : PC20
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : DB계정명 리스트 조회
	 */
	@Override
	public List<String> accountList(String user_id) {
		return historyDao.accountList(user_id);
	}
	
	/**
	 * 
	* Method : accountAndChangedList
	* 작성자 : PC20
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB 계정명 / 변경일자 리스트 조회
	 */
	@Override
	public List<HistoryVO> changedList(String account_id) {
		return historyDao.changedList(account_id);
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

package kr.or.ddit.history.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.history.dao.IHistoryDao;
import kr.or.ddit.history.model.ChangedVO;
import kr.or.ddit.history.model.HistoryVO;

@Service
public class HistoryService implements IHistoryService{

	@Resource(name = "historyDao")
	private IHistoryDao historyDao;
	
	
	/**
	 * 
	* Method : changedMainList
	* 작성자 : 강호길
	* 변경이력 :
	* @param user_id_fk
	* @return
	* Method 설명 : DB 계정명 / 변경일자 리스트 조회
	 */
	@Override
	public List<ChangedVO> changedMainList(String user_id_fk) {
		
		List<ChangedVO> totalList = new ArrayList<ChangedVO>();
		
		List<ChangedVO> changedMainList= historyDao.changedMainList(user_id_fk);
		
		for(ChangedVO cVo : changedMainList) {
			if(changedMainList.size() <= 4)
			totalList.add(cVo);
		}
		return totalList;
		
		
	}
	
	
	/**
	 * 
	* Method : changedPagingList
	* 작성자 : 강호길
	* 변경이력 :
	* @param pageMap
	* @return
	* Method 설명 : DB 계정명 / 변경일자 페이징리스트 조회
	 */
	@Override
	public Map<String, Object> changedPagingList(Map<String, Object> pageMap) {
		
		// 회원 페이징 처리
		List<HistoryVO> changedPagingList = historyDao.changedPagingList(pageMap);
		
		// 해당 회원의 DB변경계정 수
		String user_id = (String) pageMap.get("user_id");
		int changedCnt = historyDao.accountCnt(user_id);
		
		// 컨트롤러에서 담아온 pageSize
		int pageSize = (int) pageMap.get("pageSize");
		int paginationSize = (int) Math.ceil((double)changedCnt / pageSize);
		
		// Map 객체에 페이징 처리된 게시글 리스트와 페이지 갯수를 담아서 리턴한다.
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("changedPagingList", changedPagingList);
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}
	
	
	
	

	/**
	 * 
	* Method : changedDetailPagingList
	* 작성자 : 강호길
	* 변경이력 :
	* @param pageMap
	* @return
	* Method 설명 : DB변경 상세 페이징 리스트 조회
	 */
	@Override
	public Map<String, Object> changedDetailPagingList(Map<String, Object> pageMap) {
		
		
		// 해당 DB변경 이력 수
		String object_owner = (String) pageMap.get("object_owner");
		int changedCnt = historyDao.historyCnt(object_owner);
		
		// 컨트롤러에서 담아온 pageSize
		int pageSize = (int) pageMap.get("pageSize");
		int paginationSize = (int) Math.ceil((double)changedCnt / pageSize);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("changedDetailPagingList", historyDao.changedDetailPagingList(pageMap));
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
		
	}


}

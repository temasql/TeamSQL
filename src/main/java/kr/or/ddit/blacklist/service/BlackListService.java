package kr.or.ddit.blacklist.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.blacklist.dao.IBlackListDao;
import kr.or.ddit.blacklist.model.BlackListVO;

@Service
public class BlackListService implements IBlackListService{

	@Resource(name = "blackListDao")
	private IBlackListDao blackListDao;

	@Override
	public int insertBlackList(BlackListVO blackListVo) {
		return blackListDao.insertBlackList(blackListVo);
	}
	
	@Override
	public BlackListVO get(String id) {
		return blackListDao.get(id);
	}
	
	@Override
	public Map<String, Object> blackList(Map<String, Object> pageMap) {
		// 검색한 내용에 해당하는 회원이 페이징 처리 되어 리스트에 담긴다
		List<BlackListVO> blackList = blackListDao.blackList(pageMap);
		
		// 검색한 내용에 해당하는 회원의 수
		String search = (String) pageMap.get("search");
		int blackListSearchCount = blackListDao.blackListSearchCount(search);
		
		// 컨트롤러에서 담아온 pageSize
		int pageSize = (int) pageMap.get("pageSize");
		int paginationSize = (int) Math.ceil((double)blackListSearchCount/pageSize);
		
		// 게시글이 없어서 paginationSize가 0 일경우 임의로 1설정
		paginationSize = paginationSize == 0 ? 1 : paginationSize;
		
		// Map객체에 페이징 처리된 게시글 리스트와 페이지의 갯수를 담아서 리턴한다
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("blackList", blackList);
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<BlackListVO> mapList =  blackListDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return blackListDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return blackListDao.delete(id);
	}

	/**
	* Method : blackListSearchCount
	* 작성자 : 이중석
	* 변경이력 :
	* @param search
	* @return
	* Method 설명 :
	*/
	@Override
	public int blackListSearchCount(String search) {
		return blackListDao.blackListSearchCount(search);
	}

	/**
	* Method : deleteBlackListMG
	* 작성자 : 이중석
	* 변경이력 :
	* @param userMap
	* @return
	* Method 설명 :
	*/
	@Override
	public int deleteBlackListMG(Map<String, Object> userMap) {
		return blackListDao.deleteBlackListMG(userMap);
	}

}

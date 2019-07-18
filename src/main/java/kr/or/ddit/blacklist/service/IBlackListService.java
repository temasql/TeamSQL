package kr.or.ddit.blacklist.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.blacklist.model.BlackListVO;

public interface IBlackListService {
	
	int insert(BlackListVO BlackListVO);
	
	BlackListVO get(String id);
	
	List<BlackListVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}

package kr.or.ddit.manager.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.manager.model.ManagerVO;

public interface IManagerService {
	
	int insert(ManagerVO managerVo);
	
	ManagerVO get(String id);
	
	List<ManagerVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}

package kr.or.ddit.manager.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.manager.model.ManagerVO;

public interface IManagerDao {

	int insert(ManagerVO managerVo);
	
	ManagerVO get(String id);
	
	List<ManagerVO> list();
	
	List<ManagerVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}

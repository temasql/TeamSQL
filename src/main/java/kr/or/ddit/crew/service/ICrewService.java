package kr.or.ddit.crew.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.crew.model.CrewVO;

public interface ICrewService {
	
	int insert(CrewVO crewVo);
	
	CrewVO get(String id);
	
	List<CrewVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}

package kr.or.ddit.crew.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.crew.model.CrewVO;

public interface ICrewDao {

	int insert(CrewVO crewVo);
	
	CrewVO get(String id);
	
	List<CrewVO> list();
	
	List<CrewVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}

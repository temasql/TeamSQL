package kr.or.ddit.crew.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.crew.dao.ICrewDao;
import kr.or.ddit.crew.model.CrewVO;

//@Service
public class CrewService implements ICrewService{

	@Resource(name = "crewDao")
	private ICrewDao crewDao;

	@Override
	public int insert(CrewVO crewVo) {
		return crewDao.insert(crewVo);
	}
	
	@Override
	public CrewVO get(String id) {
		return crewDao.get(id);
	}
	
	@Override
	public List<CrewVO> list() {
		return crewDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<CrewVO> mapList =  crewDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return crewDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return crewDao.delete(id);
	}

}

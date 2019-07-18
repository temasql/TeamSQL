package kr.or.ddit.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.manager.dao.IManagerDao;
import kr.or.ddit.manager.model.ManagerVO;

//@Service
public class ManagerService implements IManagerService{

	@Resource(name = "managerDao")
	private IManagerDao managerDao;

	@Override
	public int insert(ManagerVO managerVo) {
		return managerDao.insert(managerVo);
	}
	
	@Override
	public ManagerVO get(String id) {
		return managerDao.get(id);
	}
	
	@Override
	public List<ManagerVO> list() {
		return managerDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ManagerVO> mapList =  managerDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return managerDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return managerDao.delete(id);
	}

}

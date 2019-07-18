package kr.or.ddit.blacklist.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.blacklist.dao.IBlackListDao;
import kr.or.ddit.blacklist.model.BlackListVO;

//@Service
public class BlackListService implements IBlackListService{

	@Resource(name = "blackListDao")
	private IBlackListDao blackListDao;

	@Override
	public int insert(BlackListVO blackListVo) {
		return blackListDao.insert(blackListVo);
	}
	
	@Override
	public BlackListVO get(String id) {
		return blackListDao.get(id);
	}
	
	@Override
	public List<BlackListVO> list() {
		return blackListDao.list();
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

}

package kr.or.ddit.domain.common_domain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.domain.common_domain.dao.ICommonDomainDao;
import kr.or.ddit.domain.common_domain.model.CommonDomainVO;

//@Service
public class CommonDomainService implements ICommonDomainService{

	@Resource(name = "commonDomainDao")
	private ICommonDomainDao commonDomainDao;

	@Override
	public int insert(CommonDomainVO commonDomainVo) {
		return commonDomainDao.insert(commonDomainVo);
	}
	
	@Override
	public CommonDomainVO get(String id) {
		return commonDomainDao.get(id);
	}
	
	@Override
	public List<CommonDomainVO> list() {
		return commonDomainDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<CommonDomainVO> mapList =  commonDomainDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return commonDomainDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return commonDomainDao.delete(id);
	}

}

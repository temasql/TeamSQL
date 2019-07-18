package kr.or.ddit.template.common_template.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.template.common_template.dao.ICommonTemplateDao;
import kr.or.ddit.template.common_template.model.CommonTemplateVO;

//@Service
public class CommonTemplateService implements ICommonTemplateService{

	@Resource(name = "commonTemplateDao")
	private ICommonTemplateDao commonTemplateDao;

	@Override
	public int insert(CommonTemplateVO commonTemplateVo) {
		return commonTemplateDao.insert(commonTemplateVo);
	}
	
	@Override
	public CommonTemplateVO get(String id) {
		return commonTemplateDao.get(id);
	}
	
	@Override
	public List<CommonTemplateVO> list() {
		return commonTemplateDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<CommonTemplateVO> mapList =  commonTemplateDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return commonTemplateDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return commonTemplateDao.delete(id);
	}

}

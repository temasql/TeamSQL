package kr.or.ddit.ts_file.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.ts_file.dao.ITSFileDao;
import kr.or.ddit.ts_file.model.TSFileVO;

//@Service
public class TSFileService implements ITSFileService{

	@Resource(name = "tsFileDao")
	private ITSFileDao tsFiledao;

	@Override
	public int insert(TSFileVO tsFileVo) {
		return tsFiledao.insert(tsFileVo);
	}
	
	@Override
	public TSFileVO get(String id) {
		return tsFiledao.get(id);
	}
	
	@Override
	public List<TSFileVO> list() {
		return tsFiledao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<TSFileVO> mapList =  tsFiledao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return tsFiledao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return tsFiledao.delete(id);
	}

}

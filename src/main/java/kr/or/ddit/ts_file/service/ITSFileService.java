package kr.or.ddit.ts_file.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.ts_file.model.TSFileVO;

public interface ITSFileService {
	
	int insert(TSFileVO tsFileVo);
	
	TSFileVO get(String id);
	
	List<TSFileVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}

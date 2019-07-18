package kr.or.ddit.ts_file.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.ts_file.model.TSFileVO;

public interface ITSFileDao {

	int insert(TSFileVO commonsVo);
	
	TSFileVO get(String id);
	
	List<TSFileVO> list();
	
	List<TSFileVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}

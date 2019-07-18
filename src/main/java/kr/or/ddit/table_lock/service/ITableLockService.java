package kr.or.ddit.table_lock.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.table_lock.model.TableLockVO;

public interface ITableLockService {
	
	int insert(TableLockVO tableLockVo);
	
	TableLockVO get(String id);
	
	List<TableLockVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}

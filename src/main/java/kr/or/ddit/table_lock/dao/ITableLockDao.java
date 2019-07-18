package kr.or.ddit.table_lock.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.table_lock.model.TableLockVO;

public interface ITableLockDao {

	int insert(TableLockVO tableLockVo);
	
	TableLockVO get(String id);
	
	List<TableLockVO> list();
	
	List<TableLockVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}

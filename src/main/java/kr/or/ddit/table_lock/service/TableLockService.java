package kr.or.ddit.table_lock.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.table_lock.dao.ITableLockDao;
import kr.or.ddit.table_lock.model.TableLockVO;

//@Service
public class TableLockService implements ITableLockService{

	@Resource(name = "tableLockDao")
	private ITableLockDao tableLockDao;

	@Override
	public int insert(TableLockVO tableLockVo) {
		return tableLockDao.insert(tableLockVo);
	}
	
	@Override
	public TableLockVO get(String id) {
		return tableLockDao.get(id);
	}
	
	@Override
	public List<TableLockVO> list() {
		return tableLockDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<TableLockVO> mapList =  tableLockDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return tableLockDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return tableLockDao.delete(id);
	}

}

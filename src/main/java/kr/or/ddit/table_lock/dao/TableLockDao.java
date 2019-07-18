package kr.or.ddit.table_lock.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.table_lock.model.TableLockVO;

//@Repository
public class TableLockDao implements ITableLockDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(TableLockVO tableLockVo) {
		return sqlSession.insert("tableLock.insert", tableLockVo);
	}
	
	@Override
	public TableLockVO get(String id) {
		return sqlSession.selectOne("tableLock.get", id);
	}
	
	@Override
	public List<TableLockVO> list() {
		return sqlSession.selectList("tableLock.list");
	}

	@Override
	public List<TableLockVO> map(Map<String, Object> map) {
		return sqlSession.selectList("tableLock.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("tableLock.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("tableLock.delete", id);
	}

	

}

package kr.or.ddit.blacklist.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.blacklist.model.BlackListVO;

public interface IBlackListDao {

	int insert(BlackListVO blackListVo);
	
	BlackListVO get(String id);
	
	List<BlackListVO> list();
	
	List<BlackListVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}

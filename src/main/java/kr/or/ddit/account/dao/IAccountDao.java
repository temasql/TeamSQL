package kr.or.ddit.account.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.account.model.AccountVO;

public interface IAccountDao {

	int insert(AccountVO accountVO);
	
	AccountVO get(String id);
	
	List<AccountVO> list();
	
	List<AccountVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}

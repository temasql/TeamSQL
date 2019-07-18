package kr.or.ddit.account.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.account.model.AccountVO;

public interface IAccountService {
	
	int insert(AccountVO accountVo);
	
	AccountVO get(String id);
	
	List<AccountVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}

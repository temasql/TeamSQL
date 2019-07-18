package kr.or.ddit.account.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.account.dao.IAccountDao;
import kr.or.ddit.account.model.AccountVO;

//@Service
public class AccountService implements IAccountService{

	@Resource(name = "accountDao")
	private IAccountDao accountDao;

	@Override
	public int insert(AccountVO accountVo) {
		return accountDao.insert(accountVo);
	}
	
	@Override
	public AccountVO get(String id) {
		return accountDao.get(id);
	}
	
	@Override
	public List<AccountVO> list() {
		return accountDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<AccountVO> mapList =  accountDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return accountDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return accountDao.delete(id);
	}

}

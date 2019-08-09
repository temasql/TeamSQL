package kr.or.ddit.manager.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.manager.dao.IManagerDao;

@Service
public class ManagerService implements IManagerService{

	@Resource(name = "managerDao")
	private IManagerDao managerDao;

	/**
	* Method : getManager
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 :
	*/
	@Override
	public String getManager(String dragText) {
		
		return null;
	}

}

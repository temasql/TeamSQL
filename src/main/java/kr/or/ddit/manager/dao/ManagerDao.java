package kr.or.ddit.manager.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.manager.model.ManagerVO;

@Repository
public class ManagerDao implements IManagerDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	* Method : getManager
	* 작성자 : 이중석
	* 변경이력 :
	* @param dragText
	* @return
	* Method 설명 :
	*/
	@Override
	public String getManager(String dragText) {
		return null;
	}


}

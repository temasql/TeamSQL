package kr.or.ddit.crew.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.crew.model.CrewVO;

@Repository
public class CrewDao implements ICrewDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 
	* Method : insertCrew
	* 작성자 : 김범휘
	* 변경이력 :
	* @param crewVO
	* @return
	* Method 설명 : 구성원 추가
	 */
	@Override
	public int insertCrew(CrewVO crewVO) {
		return sqlSession.insert("crew.insertCrew", crewVO);
	}
	

}

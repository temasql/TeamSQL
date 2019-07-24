package kr.or.ddit.crew.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.crew.dao.ICrewDao;
import kr.or.ddit.crew.model.CrewVO;

@Service
public class CrewService implements ICrewService{
	
	@Resource(name = "crewDao")
	private ICrewDao crewDao;
	
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
		return crewDao.insertCrew(crewVO);
	}


}

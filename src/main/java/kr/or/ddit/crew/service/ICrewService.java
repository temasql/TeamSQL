package kr.or.ddit.crew.service;

import kr.or.ddit.crew.model.CrewVO;

public interface ICrewService {
	
	/**
	 * 
	* Method : insertCrew
	* 작성자 : 김범휘
	* 변경이력 :
	* @param crewVO
	* @return
	* Method 설명 : 구성원 추가
	 */
	int insertCrew(CrewVO crewVO);
	
}

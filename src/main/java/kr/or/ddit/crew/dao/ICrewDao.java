package kr.or.ddit.crew.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.crew.model.CrewVO;

public interface ICrewDao {
	
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
	
	List<CrewVO>crewList(Map<String, Object>pageMap);
	
	int crewSearchCount(Map<String, Object> searchMap);
	
	List<CrewVO> crewSelectList(String user_id);

	/**
	* Method : deleteCrew
	* 작성자 : 이중석
	* 변경이력 :
	* @param crewVO
	* @return
	* Method 설명 :
	*/
	int deleteCrew(CrewVO crewVO);
	
}

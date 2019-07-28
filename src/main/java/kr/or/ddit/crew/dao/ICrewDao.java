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
	
	/**
	* Method : crewList
	* 작성자 : 이중석
	* 변경이력 :
	* @param pageMap
	* @return
	* Method 설명 : 구성원 리스트 페이징 처리
	*/
	List<CrewVO>crewList(Map<String, Object>pageMap);
	
	/**
	* Method : crewSearchCount
	* 작성자 : 이중석
	* 변경이력 :
	* @param search
	* @return
	* Method 설명 : 해당 DB계정의 구성원 수
	*/
	int crewSearchCount(Map<String, Object> searchMap);
	
	/**
	* Method : crewSelectList
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 해당 계정의 팀 리스트
	*/
	List<CrewVO> crewSelectList(String user_id);

	/**
	* Method : deleteCrew
	* 작성자 : 이중석
	* 변경이력 :
	* @param crewVO
	* @return
	* Method 설명 :구성원 삭제
	*/
	int deleteCrew(CrewVO crewVO);
	
}

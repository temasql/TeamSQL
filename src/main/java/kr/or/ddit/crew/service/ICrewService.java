package kr.or.ddit.crew.service;

import java.util.List;
import java.util.Map;

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
	
	/**
	* Method : crewList
	* 작성자 : 이중석
	* 변경이력 :
	* @param pageMap
	* @return
	* Method 설명 : 구성원 리스트 페이징 처리
	*/
	Map<String, Object> crewList(Map<String, Object> pageMap);

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
	* Method 설명 :
	*/
	int deleteCrew(CrewVO crewVO);

	/**
	* Method : getCrew
	* 작성자 : 이중석
	* 변경이력 :
	* @param crewVo
	* @return
	* Method 설명 :초대전에 이미 구성원이 있는지 null체크
	*/
	CrewVO getCrew(CrewVO crewVo);
	
}

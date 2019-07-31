package kr.or.ddit.crew.dao;

import java.util.List;
import java.util.Map;

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

	/**
	* Method : crewList
	* 작성자 : 이중석
	* 변경이력 :
	* @param pageMap
	* @return
	* Method 설명 : 구성원 리스트 페이징 처리
	*/
	@Override
	public List<CrewVO> crewList(Map<String, Object> pageMap) {
		return sqlSession.selectList("crew.crewList", pageMap);
	}

	/**
	* Method : crewSearchCount
	* 작성자 : 이중석
	* 변경이력 :
	* @param search
	* @return
	* Method 설명 : 해당 DB계정의 구성원 수
	*/
	@Override
	public int crewSearchCount(Map<String, Object> searchMap) {
		return sqlSession.selectOne("crew.crewSearchCount", searchMap);
	}

	/**
	* Method : crewSelectList
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 해당 계정의 팀 리스트
	*/
	@Override
	public List<CrewVO> crewSelectList(String user_id) {
		return sqlSession.selectList("crew.crewSelectList", user_id);
	}

	/**
	* Method : deleteCrew
	* 작성자 : 이중석
	* 변경이력 :
	* @param crewVO
	* @return
	* Method 설명 : 구성원 삭제
	*/
	@Override
	public int deleteCrew(CrewVO crewVO) {
		return sqlSession.delete("crew.deleteCrew", crewVO);
	}

	/**
	* Method : getCrew
	* 작성자 : 이중석
	* 변경이력 :
	* @param crewVo
	* @return
	* Method 설명 :초대전에 이미 구성원이 있는지 null체크
	*/
	@Override
	public CrewVO getCrew(CrewVO crewVo) {
		return sqlSession.selectOne("crew.getCrew", crewVo);
	}

	/**
	 * 
	* Method : getMyAccountList
	* 작성자 : 김범휘
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 자신이 속한 DB계정 아이디 리스트 가져오기
	 */
	@Override
	public List<String> getMyAccountList(String user_id) {
		return sqlSession.selectList("crew.getMyAccountList", user_id);
	}
	

}

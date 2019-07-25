package kr.or.ddit.worksheet.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MyBatisUtil;

public class WorksheetDao implements IWorksheetDao {

	/**
	 * 
	* Method : selectQuery
	* 작성자 : 김범휘
	* 변경이력 :
	* @param value
	* @return
	* Method 설명 : select에 관한 쿼리문 실행
	 */
	@Override
	public List<Map<String, String>> selectQuery(String value) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<Map<String, String>> resultList = sqlSession.selectList("worksheet.selectQuery", value);
		sqlSession.close();
		return resultList;
	}

	
}

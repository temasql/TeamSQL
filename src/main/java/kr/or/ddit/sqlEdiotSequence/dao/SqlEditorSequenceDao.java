/**
 * 
 */
package kr.or.ddit.sqlEdiotSequence.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.sqlEdiotSequence.model.DetailSeqVO;
import kr.or.ddit.sqlEdiotSequence.model.SelectSeqVO;

/**
 * 
* SqlEditorSequenceDao.java
*
* @author 강호길
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* PC20 최초 생성
*
* </pre>
 */
@Repository
public class SqlEditorSequenceDao implements ISqlEditorSequenceDao {

	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	/**
	* Method : createTable
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 테이블패키지 우클릭 후 테이블 생성 
	*/
	@Override
	public int createSequence(String query) {
		return sqlSession.insert("sqlEditorSequence.createSequence", query);
	}
	
	/**
	 * 
	* Method : selectSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param seqVO
	* @return
	* Method 설명 : 시퀀스 조회
	 */
	@Override
	public String selectSequence(SelectSeqVO seqVO) {
		return sqlSession.selectOne("sqlEditorSequence.selectSequence",seqVO);
	}

	/**
	 * 
	* Method : selectSequenceTable
	* 작성자 : 강호길
	* 변경이력 :
	* @param seqVO
	* @return
	* Method 설명 : 시퀀스 세부 정보 조회
	 */
	@Override
	public DetailSeqVO selectSequenceTable(Map<String, String> map) {
		return sqlSession.selectOne("sqlEditorSequence.selectSequenceTable",map);
	}

	/**
	 * 
	* Method : beforeSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 시퀀스 편집 전 데이터
	 */
	@Override
	public SelectSeqVO beforeSequence(Map<String, String> map) {
		return sqlSession.selectOne("sqlEditorSequence.beforeSequence", map);
	}
	
	/**
	 * 
	* Method : updateSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 시퀀스 편집
	 */
	@Override
	public boolean updateSequence(String query, Connection conn) {
//		return sqlSession.update("sqlEditorSequence.updateSequence", query);
		
		Statement stmt = null;
		
		boolean result = false;
		
		try {
			stmt = conn.createStatement();
			result = stmt.execute(query);
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		} finally {
			if (stmt != null)
			try {
				stmt.close();
			} catch (Exception e2) {
			}{
				
			}
		}
		return result;
	}

	/**
	 * 
	* Method : deleteSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param seqVO
	* @return
	* Method 설명 : 시퀀스 삭제
	 */
	@Override
	public int deleteSequence(String query) {
		return sqlSession.delete("sqlEditorSequence.deleteSequence",query);
	}


}

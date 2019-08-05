/**
 * 
 */
package kr.or.ddit.sqlEdiotSequence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.dbObject.model.SequenceVO;
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
	public SelectSeqVO selectSequence(SelectSeqVO seqVO) {
		return sqlSession.selectOne("sqlEditorSequence.selectSequence",seqVO);
	}
	


}

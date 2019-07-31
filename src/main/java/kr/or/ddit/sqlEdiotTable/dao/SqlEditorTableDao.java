/**
 * 
 */
package kr.or.ddit.sqlEdiotTable.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/**
* SqlEditorTable.java
*
* @author 이중석
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* 이중석 최초 생성
*
* </pre>
*/
@Repository
public class SqlEditorTableDao implements ISqlEditorTableDao {

	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	/**
	* Method : createTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 :
	*/
	@Override
	public int createTable(String query) {
		return sqlSession.update("sqlEditorTable.createTable", query);
	}

}

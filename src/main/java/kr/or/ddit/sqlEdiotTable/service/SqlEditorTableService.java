/**
 * 
 */
package kr.or.ddit.sqlEdiotTable.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.sqlEdiotTable.dao.ISqlEditorTableDao;
import kr.or.ddit.util.CreateTableUtil;
import kr.or.ddit.util.SelectTableUtil;

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
@Service
public class SqlEditorTableService implements ISqlEditorTableService {

	
	@Resource(name = "sqlEditorTableDao")
	private ISqlEditorTableDao sqlEditorTableDao;
	/**
	* Method : createTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 테이블 패키지 우클릭 후 테이블 생성
	*/
	@Override
	public int createTable(String[][] array) {
		Map<String, Object> queryMap = new CreateTableUtil().getQuery(array);
		String createTableStr = (String) queryMap.get("query");
		sqlEditorTableDao.createTable(createTableStr);
		List<String> commentQueryList = (List<String>) queryMap.get("commentQueryList");
		if (commentQueryList.size() > 0) {
			for (String comment : commentQueryList) {
				sqlEditorTableDao.createTable(comment);
			}
		}
		return 0;
	}
	/**
	* Method : selectTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param select
	* @param conn
	* @return
	* Method 설명 : 테이블 우클릭 후 테이블 조회
	*/
	@Override
	public List<List<String>> selectTable(String select, String TableName, Connection conn) {
		String query = SelectTableUtil.selectQuery(select, TableName);
		return sqlEditorTableDao.selectTable(query, conn);
	}
	
	/**
	 * 
	* Method : getColumns
	* 작성자 : 김범휘
	* 변경이력 :
	* @param tableName
	* @param conn
	* @return
	* Method 설명 : 트리거 생성에서 테이블 선택 시 그 테이블에 대한 컬럼명 리스트로 가져오기 
	 */
	@Override
	public List<String> getColumns(String tableName, Connection conn) {
		return sqlEditorTableDao.getColumns(tableName, conn);
	}

}

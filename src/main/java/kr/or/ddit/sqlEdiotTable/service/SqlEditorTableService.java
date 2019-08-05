/**
 * 
 */
package kr.or.ddit.sqlEdiotTable.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	
	private static final Logger logger = LoggerFactory.getLogger(SqlEditorTableService.class);
	
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
	/**
	* Method : updateTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param select
	* @param tableName
	* @return
	* Method 설명 :
	*/
	@Override
	public Map<String, Object> updateTable(String select, String tableName, Connection conn) {
		Map<String, Object> updateTableMap = new HashMap<String, Object>();
		if (select.equals("column")) {
			// 데이터 타입 받는 리스트 추가해야함
			String query = SelectTableUtil.selectQuery(select, tableName);
			logger.debug("query ==> {}", query);
			logger.debug("conn ==>!@ {}", conn);
			List<List<String>> columnList =  sqlEditorTableDao.selectTable(query, conn);
			String html = "sqlEditor/ajaxHtml/updateTableAjaxHtml";
			updateTableMap.put("html", html);
			updateTableMap.put("updateTable", columnList);
			return updateTableMap;
		}
		logger.debug("conn ==> {}", conn);
		
		return updateTableMap;
	}
	
}

/**
 * 
 */
package kr.or.ddit.sqlEdiotTable.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import kr.or.ddit.sqlEdiotTable.model.SqlEditorTableVO;

/**
* ISqlEditorTable.java
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
public interface ISqlEditorTableService {

	/**
	* Method : createTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 우클릭 이벤트로 테이블을 생성하는 메서드
	*/
	int createTable(String[][] query, String tableSelect);
	
	/**
	* Method : selectTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 우클릭 이벤트로 테이블을 조회 하는메서드
	*/
	List<List<String>> selectTable(String select, String TableName, Connection conn);
	
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
	List<String> getColumns(String tableName, Connection conn);

	/**
	* Method : updateTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param select
	* @param tableName
	* @return
	* Method 설명 :
	*/
	List<SqlEditorTableVO> updateTable(String tableName, Connection conn);
	
	int updateTable(String[][] query, String tableSelect);

	/**
	* Method : deleteTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param account_id
	* @param tableName
	* Method 설명 :
	*/
	void deleteTable(String account_id, String tableName);
	
	/**
	* Method : tableExport
	* 작성자 : 이중석
	* 변경이력 :
	* @param TableName
	* @param account_id
	* @param conn
	* @return
	* Method 설명 : 테이블 데이터 익스포트
	*/
	String tableExport(String tableName, String account_id, HttpSession session
			,String[]exportChecked);

	
	/**
	* Method : createVO
	* 작성자 : 이영은
	* 변경이력 :
	* @param tableName
	* @param conn
	* @return
	* Method 설명 : 자바모델생성 
	*/
	String createVO(String tableName, Connection conn);
}

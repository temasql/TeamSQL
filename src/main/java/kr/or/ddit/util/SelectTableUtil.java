/**
 * 
 */
package kr.or.ddit.util;

import java.util.List;

import kr.or.ddit.sqlEdiotTable.model.SqlEditorTableVO;

/**
* SelectTableUtil.java
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
* 테이블 우클릭 후 테이블 조회할때 어떤걸 조회할지 구분 후 반환해줄 클래스
* </pre>
*/
public class SelectTableUtil {

	/**
	* Method : selectQuery
	* 작성자 : 이중석
	* 변경이력 :
	* @param select
	* @param tableName
	* @return
	* Method 설명 : selectBox에서 선택한 정보를 확인하는 쿼리를 반환하는 메서드
	*/
	public static String selectQuery(String select, String tableName) {
		SelectTableUtil stu = new SelectTableUtil();
		if (select.equals("column"))  return stu.getColumn(tableName);
		if (select.equals("data"))  return stu.getData(tableName);
		if (select.equals("constraint"))  return stu.getConstraint(tableName);
		if (select.equals("trigger"))  return stu.getTrigger(tableName);
		if (select.equals("detail"))  return stu.getDetail(tableName);
		if (select.equals("index"))  return stu.getIndex(tableName);
		if (select.equals("DDL"))  return stu.getDDL(tableName);
		if (select.equals("CnNdT"))  return stu.getColNameNDataType(tableName);
		return "";
	}
	
	public static String selectTableDDLQuery(String checked, String tableName, String account_id) {
		SelectTableUtil stu = new SelectTableUtil();
		if (checked.equals("CONSTRAINT")) return stu.getConstraint(tableName, account_id);
		if (checked.equals("INDEX")) return stu.getIndexList(tableName);
		if (checked.equals("VIEW")) return stu.getView(account_id);
		if (checked.equals("TRIGGER")) return stu.getTrigger(tableName, account_id);
		return "";
	}
	
	private String getConstraint(String tableName, String account_id) {
		return "SELECT CONSTRAINT_NAME FROM DBA_CONSTRAINTS WHERE TABLE_NAME = '" + tableName +"' AND OWNER = '" + account_id.toUpperCase() + "'";
	}
	private String getIndexList(String tableName) {
		return "SELECT  A.INDEX_NAME FROM DBA_IND_COLUMNS A WHERE A.TABLE_NAME = '" + tableName + "' ORDER BY A.INDEX_NAME, A.COLUMN_POSITION";
	}
	private String getView(String account_id) {
		return "SELECT VIEW_NAME FROM DBA_VIEWS WHERE OWNER = '" + account_id.toUpperCase() + "'";
	}
	private String getTrigger(String tableName, String account_id) {
		return "SELECT TRIGGER_NAME FROM DBA_TRIGGERS WHERE TABLE_OWNER = '" + account_id.toUpperCase() + "' AND TABLE_NAME = '" + tableName + "'";
	}

	/**
	* Method : getColNameNDataType
	* 작성자 : 이중석
	* 변경이력 :
	* @param tableName
	* @return
	* Method 설명 : 테이블의 컬럼 데이터 타입과 이름을 조회하는 쿼리를 반환하는 메서드 
	*/
	private String getColNameNDataType(String tableName) {
		return "select COLUMN_NAME, DATA_TYPE from USER_TAB_COLUMNS WHERE TABLE_NAME = '" + tableName + "'"; 
	}
	
	/**
	* Method : getDDL
	* 작성자 : 이중석
	* 변경이력 :
	* @param tableName
	* @return
	* Method 설명 : 테이블 DDL조회
	*/
	private String getDDL(String tableName) {
		return "SELECT DBMS_METADATA.GET_DDL('TABLE', '" + tableName + "') FROM DUAL";
	}
	
	/**
	* Method : getIndex
	* 작성자 : 이중석
	* 변경이력 :
	* @param tableName
	* @return
	* Method 설명 : 테이블 인덱스 조회
	*/
	private String getIndex(String tableName) {
		return "SELECT A.uniqueness, b.INDEX_NAME, b.TABLE_NAME, b.COLUMN_NAME FROM USER_INDEXES a, USER_IND_COLUMNS b WHERE a.index_name = b.index_name AND a.table_name='" + tableName + "'";
	}
	
	/**
	* Method : getDetail
	* 작성자 : 이중석
	* 변경이력 :
	* @param tableName
	* @return
	* Method 설명 : 테이블 세부 정보 조회
	*/
	private String getDetail(String tableName) {
		StringBuffer getDetailQuery = new StringBuffer();
		getDetailQuery.append("SELECT TABLE_NAME");
		getDetailQuery.append(", TABLESPACE_NAME");
		getDetailQuery.append(", STATUS");
		getDetailQuery.append(", PCT_FREE");
		getDetailQuery.append(", PCT_USED");
		getDetailQuery.append(", INI_TRANS");
		getDetailQuery.append(", MAX_TRANS");
		getDetailQuery.append(", INITIAL_EXTENT");
		getDetailQuery.append(" FROM USER_TABLES");
		getDetailQuery.append(" WHERE TABLE_NAME LIKE '" + tableName + "'");
		return getDetailQuery.toString();
	}
	/**
	* Method : getTrigger
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 : 테이블의 트리거 정보 조회
	*/
	private String getTrigger(String tableName) {
		return "SELECT TRIGGER_NAME, TRIGGER_TYPE, TRIGGERING_EVENT, STATUS, TABLE_NAME  FROM USER_TRIGGERS WHERE TABLE_NAME ='" + tableName + "'";
	}
	/**
	* Method : getConstraint
	* 작성자 : 이중석
	* 변경이력 :
	* @param tableName
	* @return
	* Method 설명 : 테이블의 제약조건 조회
	*/
	private String getConstraint(String tableName) {
		return "SELECT OWNER, CONSTRAINT_NAME, CONSTRAINT_TYPE, TABLE_NAME, STATUS, LAST_CHANGE, INDEX_OWNER, INDEX_NAME FROM USER_CONSTRAINTS	WHERE TABLE_NAME='"+ tableName+ "'";
	}
	
	/**
	* Method : getData
	* 작성자 : 이중석
	* 변경이력 :
	* @param tableName
	* @return
	* Method 설명 : 테이블의 데이터 조회
	*/
	private String getData(String tableName) {
		return "SELECT * FROM " + tableName;
	}
	
	
	/**
	* Method : getColumn
	* 작성자 : 이중석
	* 변경이력 :
	* @param tableName
	* @return
	* Method 설명 : 테이블의 컬럼 조회
	*/
	private String getColumn(String tableName) {
		StringBuffer getColumnQuery = new StringBuffer();
		getColumnQuery.append("SELECT "); 
		getColumnQuery.append("A.COLUMN_ID");
		getColumnQuery.append(", A.COLUMN_NAME");
		getColumnQuery.append(", A.DATA_TYPE ||'('||"); 
		getColumnQuery.append(" DECODE(");
		getColumnQuery.append("A.DATA_TYPE");
		getColumnQuery.append(", 'NUMBER'");
		getColumnQuery.append(", A.DATA_PRECISION ||");
		getColumnQuery.append(" DECODE(A.DATA_SCALE, 0, '',' ' || A.DATA_SCALE)");
		getColumnQuery.append(", A.DATA_LENGTH)||')' as data_type");
		getColumnQuery.append(", A.NULLABLE");
		getColumnQuery.append(", A.DATA_DEFAULT");
		getColumnQuery.append(", B.COMMENTS");
		getColumnQuery.append(" FROM");
		getColumnQuery.append(" USER_TAB_COLUMNS A, USER_COL_COMMENTS B");
		getColumnQuery.append(" WHERE");
		getColumnQuery.append(" A. TABLE_NAME = B.TABLE_NAME"); 
		getColumnQuery.append(" AND");
		getColumnQuery.append(" A.COLUMN_NAME = B.COLUMN_NAME");
		getColumnQuery.append(" AND");
		getColumnQuery.append(" A.TABLE_NAME = '" + tableName + "'");
		getColumnQuery.append(" ORDER BY"); 
		getColumnQuery.append(" A.COLUMN_ID");
		return getColumnQuery.toString();
	}
	
	/**
	* Method : primaryKeyInjection
	* 작성자 : 이중석
	* 변경이력 :
	* @param sqlEditorTablePrimaryKeyList
	* @param sqlEditorTableColumnDataList
	* @return
	* Method 설명 : 컬럼리스트를 조회 후 pk가 존재하면 pk값을 true로 전환
	*/
	public static List<SqlEditorTableVO> primaryKeyInjection(List<String>primaryKeyList, List<SqlEditorTableVO> columnDataList ){
		for (SqlEditorTableVO sqlEditorTableColumn : columnDataList) {
			if (primaryKeyList != null && primaryKeyList.size() > 0) {
				for (String pk : primaryKeyList) {
					if(sqlEditorTableColumn.getColumn_name().equals(pk)) {
						sqlEditorTableColumn.setPk("true");
						break;
					}else {
						sqlEditorTableColumn.setPk("false");
						continue;
					}
				}
			}else {
				sqlEditorTableColumn.setPk("false");
			}
		}
		return columnDataList;
	}
	
}

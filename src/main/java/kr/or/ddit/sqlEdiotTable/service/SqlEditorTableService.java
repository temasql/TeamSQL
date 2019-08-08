/**
 * 
 */
package kr.or.ddit.sqlEdiotTable.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import kr.or.ddit.account.dao.IAccountDao;
import kr.or.ddit.account.model.AccountVO;
import kr.or.ddit.sqlEdiotTable.dao.ISqlEditorTableDao;
import kr.or.ddit.sqlEdiotTable.model.SqlEditorTableVO;
import kr.or.ddit.util.CreateTableUtil;
import kr.or.ddit.util.DBUtilForWorksheet;
import kr.or.ddit.util.SelectTableUtil;
import kr.or.ddit.util.TableExportUtil;

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
public class SqlEditorTableService extends CreateTableUtil implements ISqlEditorTableService {

	
	// 테이블 수정전 테이블의 정보를 담을 리스트
	private List<SqlEditorTableVO> columnDataList = null;
	
	// 해당 테이블의 PK 컬럼이름을 담을 리스트
	private List<String> primaryKeyList = null;
	
	// 테이블 수정후 테이블의 정보를 담을 리스트
	List<String> updateTableColumnList = null;
	
	@Resource(name = "sqlEditorTableDao")
	private ISqlEditorTableDao sqlEditorTableDao;
	
	@Resource(name = "accountDao")
	private IAccountDao accountDao;
	
	/**
	* Method : createTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 테이블을 생성할때 필요한 정보를 json형식의 2차원 배열으로 받아
	* 가공하여 mybatis에서 처리하는 메서드
	*/
	@Override
	public int createTable(String[][] array, String tableSelect) {
		
		// 테이블과 코멘트를 생성하는 쿼리 맵
		Map<String, Object> queryMap = getQuery(array, tableSelect);
		
		// 테이블 생성 문자열
		String createTableStr = (String) queryMap.get("query");
		sqlEditorTableDao.createTable(createTableStr);
		
		// 컬럼당 코멘트 생성 쿼리 리스트 ( 공통)
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
		return sqlEditorTableDao.selectTable(SelectTableUtil.selectQuery(select, TableName), conn);
	}
	/**
	* Method : tableDataExport
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 : 테이블 익스포트 
	*/
	public String tableExport(String tableName, String account_id, HttpSession session
			, String[]exportChecked){
		
		// 스키마.테이블이름
		String stNm = account_id + "." + tableName ;
		String query = "";
		AccountVO accountVO = accountDao.getAccountOne(account_id);
		Connection conn = DBUtilForWorksheet.getConnection(account_id, accountVO.getAccount_pw(), session);
		StringBuffer str = new StringBuffer();
		
		str.append(sqlEditorTableDao.getDDL("TABLE", account_id, tableName));
		
		if (exportChecked.length > 0) {
			for (String checked : exportChecked) {
				query = SelectTableUtil.selectTableDDLQuery(checked, tableName, account_id);
				if (checked.equals("CONSTRAINT")) {
					List<String> ddlList = sqlEditorTableDao.getData(query);
					for (String ddl : ddlList) {
						str.append(sqlEditorTableDao.getDDL(checked ,account_id, ddl));
					}
				}
				if (checked.equals("INDEX")) {
					List<String> ddlList = sqlEditorTableDao.getIndexes(query);
					for (String ddl : ddlList) {
						str.append(sqlEditorTableDao.getDDL(checked ,account_id, ddl));
					}
				}
				if (checked.equals("VIEW")) {
					List<String> ddlList = sqlEditorTableDao.getViews(query);
					for (String ddl : ddlList) {
						str.append(sqlEditorTableDao.getDDL(checked ,account_id, ddl));
					}
				}
				if (checked.equals("TRIGGER")) {
					List<String> ddlList = sqlEditorTableDao.getTriggers(query);
					for (String ddl : ddlList) {
						str.append(sqlEditorTableDao.getDDL(checked ,account_id, ddl));
					}
				}
				if (checked.equals("DATA")) {
					str.append(dataExport(tableName, stNm, conn));
				}
			}
		}
		
		str.append(sqlEditorTableDao.getCommentDDL(account_id, tableName));
		
		return str.toString();
	}
	
	/**
	* Method : dataExport
	* 작성자 : 이중석
	* 변경이력 :
	* @param tableName
	* @param stNm
	* @param conn
	* @return
	* Method 설명 : 데이터 익스포트
	*/
	private String dataExport(String tableName, String stNm, Connection conn){
		
		// 데이터 익스포트 하기 위해 컬럼의 이름과 데이터를 가져온다.
		List<List<String>> dataList = sqlEditorTableDao.selectTable(SelectTableUtil.selectQuery("data", tableName), conn);
		
		// 테이블의 컬럼이름과 데이터 타입이 들어있는 메서드
		List<List<String>> colNameNDataTypeList = sqlEditorTableDao.selectTable(SelectTableUtil.selectQuery("CnNdT", tableName), conn);
		
		// 컬럼의 이름이 들어있는 리스트
		List<String> columnNameList = dataList.get(0);
		
		// 테이블의 컬럼중 크기가 들어가면 안되는 컬럼 리스트
		List<String> nosizeColList = TableExportUtil.getNoSizeColList(colNameNDataTypeList, columnNameList);
		
		List<String> insertList = TableExportUtil.getInsertQueryList(stNm,dataList,columnNameList, nosizeColList);
		StringBuffer sbf = new StringBuffer();
		for (String dt : insertList) {
			sbf.append(dt + "\r\n");
		}
		
		return sbf.toString();
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
	* Method 설명 : 해당 테이블의 컬럼 정보를 반환
	*/
	@Override
	public List<SqlEditorTableVO> updateTable(String tableName, Connection conn) {
		
		// 해당 테이블의 PK 컬럼이름 리스트
		primaryKeyList = sqlEditorTableDao.selectTablePrimaryKey(tableName, conn);
		
		// 해당 테이블의 컬럼리스트에 pk가 존재하면 해당컬럼의 pk값을 true로 전환
		columnDataList = SelectTableUtil.primaryKeyInjection(primaryKeyList, sqlEditorTableDao.selectTableColumnData(tableName, conn));
		return columnDataList;
	}
	
	
	/**
	* Method : getColumnName
	* 작성자 : 이중석
	* 변경이력 :
	* @param queryInColumn
	* @return
	* Method 설명 : 컬럼을 수정하는 쿼리에서 컬럼의 이름만 추출하는 작업 
	*/
	private String getColumnName(String queryInColumn) {
		
		// ex ) "ALTER TABLE SCHEMA.TABLENAME MODIFY '('user_id " 소괄호의 인덱스를 구함 
		int startidx = queryInColumn.indexOf("(");
		
		// ex) " ALTER TABLE SCHEMA.TABLENAME MODIFY '('user_id VARCHAR2(20) NOTNULL )" ==> "(user_id VARCHAR2(20) NOTNULL )"
		String slice = queryInColumn.substring(startidx);
		
		// ex) "(user_id' 'VARCHAR2(20)NOTNULL)" ==> (user_id
		int endidx = slice.indexOf(" ");
		
		// return user_id
		return slice.substring(1, endidx);
	}
	/**
	* Method : updateTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 테이블 편집에 맞게 쿼리를 작성 후 응답한다.
	*/
	@Override
	public int updateTable(String[][] array, String tableSelect) {
		// 테이블 수정에 필요한 쿼리가 담겨있는 맵
		Map<String, Object> queryMap = getQuery(array, tableSelect);

		// 변경된 컬럼을 수정하는 쿼리가 들어있는 리스트
		updateTableColumnList = (List<String>) queryMap.get("queryList");
		
		// PK로 선택한 컬럼으로 제약조건을 생성하는 쿼리
		String pkQuery = (String) queryMap.get("pkQuery");
		
		// PK체크를 한 컬럼의 이름이 담긴 리스트
		List<String> pkColNameList = (List<String>) queryMap.get("pkColumnList");
		
		// 컬럼설명을 작성한 컬럼의 코멘트 생성 쿼리 리스트
		List<String> commentQueryList = (List<String>) queryMap.get("commentQueryList");
		
		// SCHEMA.TABLENAME ex) TeamSQL.USERS
		String stNm = (String) queryMap.get("stNm");
		
		// 해당 테이블의 컬럼의 수와 변경된 컬럼의 수가 같을때  컬럼 정보 수정 메서드 호출
		if (columnDataList.size() == updateTableColumnList.size()) renameColumn(array, stNm);
		
		// 수정되기 전 PK리스트의 컬럼이름과 수정 후 PK 리스트의 컬럼이름이 일치하면  
		// 제약조건 생성 쿼리를 담은 변수의 값을 null로 변경
		if (primaryKeyList.equals(pkColNameList)) pkQuery = null;
		
		// 수정되기 전 PK리스트가 존재하고 
		// 테이블 수정전과 수정후의 PK컬럼수가 다르거나
		// 테이블 수정전 PK컬럼리스트와 수정후 리스트가 다르면 
		if (primaryKeyList.size() > 0 &&
				(primaryKeyList.size() != pkColNameList.size() ||
				!primaryKeyList.equals((pkColNameList)))) {
			
			// 제약조건 삭제 쿼리 실행
			dropConstraint();
			
			// 수정되기 전 PK리스트가 존재하고 수정 후 PK리스트가 없으면 
			// 제약조건 생성 쿼리를 담은 변수의 값을 null로 변경
			if(primaryKeyList.size() > 0 && pkColNameList.size() == 0) pkQuery = null;
		} 
		
		// 수정전 컬럼의 수가 수정후의 컬럼의 수보다 크다면 수정 후 제거한 컬럼을 DB 테이블에서삭제 
		if (columnDataList.size() > updateTableColumnList.size()) dropcolumn(stNm);

		// 수정후의 컬럼수가 수정전의 컬럼수보다 크면
		if (columnDataList.size() < updateTableColumnList.size()) { 
			columnDeduplicationList();
			
			// 커진 수만큼 반복하여 컬럼을 생성
			for (String updateColumn : updateTableColumnList) 
				sqlEditorTableDao.createTable(updateColumn);
		}
		// 제약조건 생성쿼리가 null이 아니면 제약조건 생성
		if (pkQuery != null ) sqlEditorTableDao.createTable(pkQuery);
		
		// 코멘트를 작성했으면 해당 컬럼의 코멘트 생성
		if (commentQueryList.size() > 0) 
			for (String comment : commentQueryList) 
				sqlEditorTableDao.createTable(comment);
		
		return 0;
	}
	
	
	/**
	* Method : columnDeduplicationList
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 : 테이블 편집중 컬럼을 추가할때 기존의 컬럼이 재추가 되지 않게 중복을 제거하는 메서드
	*/
	private void columnDeduplicationList(){
		// 컬럼의 이름을 모두 담을 stack
		LinkedList<String> stack = new LinkedList<String>();
		
		// 컬럼의 이름을 담을 변수
		String column = "";
		
		// 기존의 컬럼수만큼 반복
		for (int i = 0; i < columnDataList.size(); i++) {
			
			// 기존의 컬럼이름을 반복하여 모두 담는다.
			stack.push(columnDataList.get(i).getColumn_name());
			
			// 수정 후의 컬럼 수 만큼 반복
			for (int j = 0; j < updateTableColumnList.size(); j++) {
				
				// 수정된 컬럼의 이름을 변수에 대입
				column = getColumnName(updateTableColumnList.get(j));
				// 테이블 수정전 컬럼의 이름이 수정후에도 존재하면 
				if (columnDataList.get(i).getColumn_name().equals(column)) {
					// stack에서 제거
					stack.pop();
					updateTableColumnList.remove(j); 
					break;
				}
			}
		}
	}
	
	/**
	* Method : dropcolumn
	* 작성자 : 이중석
	* 변경이력 :
	* @param stNm
	* Method 설명 : 수정 후 컬럼을 삭제 시 해당 컬럼의 이름을 추출하여 컬럼을 삭제
	*/
	private void dropcolumn(String stNm) {
		
		// 컬럼의 이름을 모두 담을 stack
		LinkedList<String> stack = new LinkedList<String>();
		
		// 컬럼의 이름을 담을 변수
		String column = "";
		
		// 기존의 컬럼수만큼 반복
		for (int i = 0; i < columnDataList.size(); i++) {
			
			// 기존의 컬럼이름을 반복하여 모두 담는다.
			stack.push(columnDataList.get(i).getColumn_name());
			
			// 수정 후의 컬럼 수 만큼 반복
			for (int j = 0; j < updateTableColumnList.size(); j++) {
				
				// 수정된 컬럼의 이름을 변수에 대입
				column = getColumnName(updateTableColumnList.get(j));
				
				// 테이블 수정전 컬럼의 이름이 수정후에도 존재하면 
				if (columnDataList.get(i).getColumn_name().equals(column)) {
					// stack에서 제거
					stack.pop();
					break;
				}
			}
		}
		
		// 수정하면서 제거한 컬럼의 이름을 넘김 
		dropQuery(stack, stNm);
	}
	
	/**
	* Method : dropQuery
	* 작성자 : 이중석
	* 변경이력 :
	* @param stack
	* @param stNm
	* Method 설명 : 수정하면서 제거한 컬럼의 이름만큼 실제 테이블에서 컬럼을 제거
	*/
	private void dropQuery(LinkedList<String> stack, String stNm) {
		for (String st : stack)
			sqlEditorTableDao.createTable("ALTER TABLE " + stNm +" DROP COLUMN " + st);
	}
	/**
	* Method : dropConstraint
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 테이블 수정중 제약조건 삭제
	*/
	private void dropConstraint() {
		String query = updateTableColumnList.get(0);
		int endIndex =query.indexOf("ADD"); 
		query = query.substring(0, endIndex);
		String dropQuery = query + "DROP CONSTRAINT " + query.substring(query.indexOf(".") + 1, endIndex - 1) + "_PK";
		
		sqlEditorTableDao.createTable(dropQuery);
		
	}
	/**
	* Method : renameColumn
	* 작성자 : 이중석
	* 변경이력 :
	* @param array
	* @param stNm
	* Method 설명 : 테이블 편집시 컬럼이름을 변경하거나 데이터 타입 변경시
	*/
	private void renameColumn(String[][] array, String stNm) {
		// 쿼리를 담을 객체
		StringBuffer modifyData = new StringBuffer();
		
		// 변경된 컬럼의 이름을 담을 변수
		String newColName = "";
		
		// 변경전 컬럼의 이름을 담을 변수
		String oldColName = "";
		
		// 해당 테이블의 컬럼 수 만큼 반복
		for (int i = 0; i < columnDataList.size(); i++) {
			
			// 변경된 컬럼의 이름
			newColName = getColumnName(updateTableColumnList.get(i));
			
			// 변경전 컬럼의 이름
			oldColName = columnDataList.get(i).getColumn_name();
			
			// 변경전과 변경후의 컬럼이름이 다르면
			if(!oldColName.equals(newColName)) {
				// 컬럼의 이름을 변경
				String renameQuery = "ALTER TABLE "+ stNm +" RENAME COLUMN " + oldColName + " TO " + newColName;
				sqlEditorTableDao.createTable(renameQuery);
			};
			
			// 컬럼의 이름 이외에 컬럼의 정보가 변했으면
			if (updateCheck(array)) {
				
				modifyData.append("ALTER TABLE " + stNm + " MODIFY (" + newColName + " "); 
				
				// 수정하는 쿼리가 담긴 리스트에서 하나를 뽑는다.
				String modifyDataQuery = updateTableColumnList.get(i);
				if (columnDataList.get(i).getNullable().equals("N") && updateTableColumnList.get(i).contains("NOT NULL")) {
					modifyDataQuery = modifyDataQuery.replaceAll("NOT NULL", "");
				}
				
				// 수정하는 쿼리 문자열에서 컬럼의 이름 까지 자른 후 컬럼의 정보를 추출한다. 
				// ex) (user_id VARCHAR2(20) NOT NULL DEFAULT 1) ==> "VARCHAR2(20) NOT NULL DEFAULT 1"
				modifyDataQuery = modifyDataQuery.substring(modifyDataQuery.lastIndexOf(newColName)  + newColName.length(), modifyDataQuery.lastIndexOf(")"));
				if (modifyDataQuery.contains("NOT NULL") && !modifyDataQuery.contains("DEFAULT NULL")) {
					int idx = modifyDataQuery.indexOf("NOT NULL");
					StringBuffer sbf = new StringBuffer(modifyDataQuery);
					sbf.insert(idx, " DEFAULT NULL ");
					modifyDataQuery = sbf.toString();
				}
				
				// 추출한 컬럼의 정보를 추가
				modifyData.append(modifyDataQuery + ")");
				// 조합한 쿼리를 실행
				sqlEditorTableDao.createTable(modifyData.toString());
				
				// 쿼리를 담은 객체 초기화
				modifyData.setLength(0);
			}
			
		}		
	}
	
	
	private String getNewData(String arr) {
		String[] tmp = arr.split(": ");
		tmp[1] = tmp[1].substring(1, tmp[1].length() - 2);
		return tmp[1];
	}
	/**
	* Method : updateCheck
	* 작성자 : 이중석
	* 변경이력 :
	* @param array
	* @return
	* Method 설명 : 컬럼의 정보가 변경되었는지 확인하는 메서드
	* 반복 중 컬럼의 정보가 하나라도 변했으면 true반환
	*/
	private boolean updateCheck(String[][] array) {
		boolean updateCk = true;
		List<Boolean> updateCkList = new ArrayList<Boolean>();
		for (int i = 0; i < array.length; i++) {
			for (int j = 2; j <= 5; j++) {
				String newData = getNewData(array[i][j]);
				String oldData = "";
				switch (j) {
				case 2:
					oldData = columnDataList.get(i).getData_type();
					break;
				case 3:
					oldData = String.valueOf(columnDataList.get(i).getData_length());
					break;
				case 4:
					oldData = columnDataList.get(i).getNullable();
					oldData = oldData == "Y" ? "false" : "true";
					break;
				case 5:
					oldData = columnDataList.get(i).getData_default();
					oldData = oldData == null ?  "" : oldData;
					break;
				}
				if(oldData.equals(newData)) {
					updateCk = false;
					updateCkList.add(updateCk);
					continue;
				}
				updateCk = true;
				updateCkList.add(updateCk);
			}
		}
		for (Boolean boolean1 : updateCkList) {
			if (boolean1) {
				return true;
			}
		}
		return false;
	}
	/**
	* Method : deleteTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param account_id
	* @param tableName
	* Method 설명 : 테이블 삭제
	*/
	@Override
	public void deleteTable(String account_id, String tableName) {
		account_id = account_id.toUpperCase();
		String stNm = "\"" + account_id + "\".\"" + tableName + "\"";
		String dropTableQuery = "drop table " + stNm + " cascade constraints PURGE";
		sqlEditorTableDao.createTable(dropTableQuery);
	}
	
}

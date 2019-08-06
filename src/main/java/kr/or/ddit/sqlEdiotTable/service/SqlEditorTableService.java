/**
 * 
 */
package kr.or.ddit.sqlEdiotTable.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.sqlEdiotTable.dao.ISqlEditorTableDao;
import kr.or.ddit.sqlEdiotTable.model.SqlEditorTableVO;
import kr.or.ddit.util.CreateTableUtil;
import kr.or.ddit.util.DataTypeUtil;
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
public class SqlEditorTableService extends CreateTableUtil implements ISqlEditorTableService {

	
	private static final Logger logger = LoggerFactory.getLogger(SqlEditorTableService.class);
	private List<String> pkList = null;
	private List<SqlEditorTableVO> postDataList = null;
	@Resource(name = "sqlEditorTableDao")
	private ISqlEditorTableDao sqlEditorTableDao;
	
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
	public int createTable(String[][] array) {
		Map<String, Object> queryMap = getQuery(array);
		String createTableStr = (String) queryMap.get("query");
		logger.debug("query ==> [{}]", createTableStr);
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
		logger.debug("updateTable getService select[{}]", select);
		if (select.equals("column")) {

			List<String> primaryKeyList = sqlEditorTableDao.selectTablePrimaryKey(tableName, conn);
			pkList = sqlEditorTableDao.selectTablePrimaryKey(tableName, conn);
			logger.debug("updateTable getService pkList ==>[{}]", primaryKeyList);
			List<SqlEditorTableVO> columnDataList = sqlEditorTableDao.selectTableColumnData(tableName, conn);
			logger.debug("updateTable getService columnList ==>[{}]", columnDataList);
			
			columnDataList = SelectTableUtil.primaryKeyInjection(primaryKeyList, columnDataList);
			logger.debug("updateTable getService columnDataList ==>[{}]", columnDataList);
			setUpdateTablePostDataList(columnDataList);
			String html = "sqlEditor/ajaxHtml/updateTableAjaxHtml";
			updateTableMap.put("html", html);
			updateTableMap.put("dataTypeList", DataTypeUtil.tableDataType());
			updateTableMap.put("columnDataList", columnDataList);
			logger.debug("updateTable getService columnDataList==>[]", columnDataList);
			return updateTableMap;
		}
		logger.debug("conn ==> {}", conn);
		
		return updateTableMap;
	}
	/**
	* Method : updateTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 :
	*/
	@Override
	public int updateTable(String[][] array) {
		logger.debug("Before*******=={}==*******",array);
		
		List<Boolean> updateDataTypeCheckList = new ArrayList<Boolean>(); 
		List<Boolean> updateDataLengthCheckList = new ArrayList<Boolean>(); 
		List<Boolean> updateNullableCheckList = new ArrayList<Boolean>(); 
		List<Boolean> updateDataDefaultCheckList = new ArrayList<Boolean>(); 
		for (int i = 0; i < array.length; i++) {
			String[] dataTypeSplit = array[i][2].split(": ");
			String newArrDataType = dataTypeSplit[1].substring(1, dataTypeSplit[1].length() - 2);
			String oldDataType = postDataList.get(i).getData_type();
			if (!oldDataType.equals(newArrDataType)) {
				updateDataTypeCheckList.add(true);
			}else {
				updateDataTypeCheckList.add(false);
			}
			String[] dataLengthSplit = array[i][3].split(": ");
			String newDataLength = dataLengthSplit[1].substring(1, dataLengthSplit[1].length() - 2);
			int oldDataLength = postDataList.get(i).getData_length();
			if (oldDataLength != Integer.parseInt(newDataLength)) {
				updateDataLengthCheckList.add(true);
			}else {
				updateDataLengthCheckList.add(false);
			}
			String[] nullableSplit = array[i][4].split(": ");
			String newNullable = nullableSplit[1].substring(1, nullableSplit[1].length() - 2);
			String oldNullable = postDataList.get(i).getNullable() == null ? "" : postDataList.get(i).getNullable();
			if (oldNullable.equals("Y")) {
				oldNullable = "false";
			} else if(oldNullable.equals("N")) {
				oldNullable = "true";
			}
			if (!oldNullable.equals(newNullable)) {
				updateNullableCheckList.add(true);
			}else {
				updateNullableCheckList.add(false);
			}
			String[] dataDefaultSplit = array[i][5].split(": ");
			String newDataDefault = dataDefaultSplit[1].substring(1, dataDefaultSplit[1].length() - 2);
			String oldDataDefault = postDataList.get(i).getData_default() == null ? "" : postDataList.get(i).getData_default();
			logger.debug("newDataDefault[{}], oldDataDefault[{}]", newDataDefault, oldDataDefault);
			if (!oldDataDefault.equals(newDataDefault)) {
				updateDataDefaultCheckList.add(true);
			}else {
				updateDataDefaultCheckList.add(false);
			}
		}
		logger.debug("updateDataTypeCheckList ==>[{}]",updateDataTypeCheckList);
		logger.debug("updateDataLengthCheckList ==>[{}]",updateDataLengthCheckList);
		logger.debug("updateNullableCheckList ==>[{}]",updateNullableCheckList);
		logger.debug("updateDataDefaultCheckList ==>[{}]",updateDataDefaultCheckList);
		logger.debug("after*******=={}==*******",array);
		Map<String, Object> queryMap = new CreateTableUtil().getUpdateQuery(array);
		List<String> updateTableColumnList = (List<String>) queryMap.get("query");
		logger.debug("postService updateTableColumnList==>[{}] ",updateTableColumnList);
		String pkQuery = (String) queryMap.get("pkQuery");
		logger.debug("updateTable postService pkQuery==>[{}] ",pkQuery);
		List<String> oldPkColList = getPkList();
		logger.debug("updateTable postService oldPkColList==>[{}] ",oldPkColList);
		List<String> pkColNameList = (List<String>) queryMap.get("pkColumnList");
		logger.debug("updateTable postService pkColNameList==>[{}] ",pkColNameList);
		int dropCnt = 0;
		String stNm = (String) queryMap.get("stNm");
		
		if (postDataList.size() == updateTableColumnList.size()) {
			StringBuffer modifyData = new StringBuffer();
			for (int i = 0; i < postDataList.size(); i++) {
				int startidx = updateTableColumnList.get(i).indexOf("(");
				String newColName = updateTableColumnList.get(i).substring(startidx);
				int endidx = newColName.indexOf(" ");
				newColName = newColName.substring(1, endidx);
				String oldColName = postDataList.get(i).getColumn_name();
				if(!oldColName.equals(newColName)) {
					String renameQuery = "ALTER TABLE "+ stNm +" RENAME COLUMN " + oldColName + " TO " + newColName;
					logger.debug("renameQuery===>[{}]", renameQuery);
					sqlEditorTableDao.createTable(renameQuery);
					updateTableColumnList.get(i).replace(oldColName, newColName);
				};
				if (updateDataTypeCheckList.get(i) || updateDataLengthCheckList.get(i) ||
					updateDataDefaultCheckList.get(i) || updateNullableCheckList.get(i)) {
					modifyData.append("ALTER TABLE " + stNm + " MODIFY (" + newColName + " "); 
					String modifyDataQuery = updateTableColumnList.get(i);
					modifyDataQuery = modifyDataQuery.substring(modifyDataQuery.lastIndexOf(newColName)  + newColName.length(), modifyDataQuery.lastIndexOf(")"));
					modifyData.append(modifyDataQuery + ")");
					logger.debug("modifyData.toString()==[{}]",modifyData.toString());
					sqlEditorTableDao.createTable(modifyData.toString());
					modifyData.setLength(0);
				}
				
			}
		}
		
		if (oldPkColList.equals(pkColNameList)) {
			pkQuery = null;
		}
		if (oldPkColList.size() > 0 &&(oldPkColList.size() != pkColNameList.size() || !oldPkColList.equals((pkColNameList)))) {
			String query = updateTableColumnList.get(0);
			int endIndex =query.indexOf("ADD"); 
			query = query.substring(0, endIndex);
			String dropQuery = query + "DROP CONSTRAINT " + query.substring(query.indexOf(".") + 1, endIndex - 1) + "_PK";
			logger.debug("updateTable postService dropQuery==>[{}] ",dropQuery);
			sqlEditorTableDao.createTable(dropQuery);
			if(oldPkColList.size() > 0 && pkColNameList.size() == 0) {
				pkQuery = null;
			}
			
		} 
		
		logger.debug("updateTable postService PDL.size ==>[{}]", postDataList.size());
		logger.debug("updateTable postService UTCL.size ==>[{}]", updateTableColumnList.size());
		logger.debug("updateTable postService PDL ==>[{}]", postDataList);
		logger.debug("updateTable postService UTCL==>[{}]", updateTableColumnList);
		if (postDataList.size() > updateTableColumnList.size()) {
			LinkedList<String> stack = new LinkedList<String>();
			for (int i = 0; i < postDataList.size(); i++) {
				stack.push(postDataList.get(i).getColumn_name());
				for (int j = 0; j < updateTableColumnList.size(); j++) {
					int startidx = updateTableColumnList.get(j).indexOf("(");
					String slice = updateTableColumnList.get(j).substring(startidx);
					int endidx = slice.indexOf(" ");
					slice = slice.substring(1, endidx);
					if (postDataList.get(i).getColumn_name().equals(slice)) {
						stack.pop();
						break;
					}
					
				}
			}
			logger.debug("**[{}]**", stack);
			for (String st : stack) {
				String dropCol = "ALTER TABLE " + stNm +" DROP COLUMN " + st;
				logger.debug("dropCol===>>[{}]",dropCol);
				sqlEditorTableDao.createTable(dropCol);
			}
		}

		logger.debug("updateTable postService updateTableColumnList==>[{}] ",updateTableColumnList);
		if (postDataList.size() < updateTableColumnList.size()) {
			for (int i = 0; i < postDataList.size(); i++) {
				logger.debug("removeColList[{}]",updateTableColumnList.remove(0));
			}
			for (String updateColumn : updateTableColumnList) {
				logger.debug("updateTable postService updateColumn==>[{}] ",updateColumn);
				sqlEditorTableDao.createTable(updateColumn);
			}
		}
		if (pkQuery != null ) {
			logger.debug("pkQuery call");
			sqlEditorTableDao.createTable(pkQuery);
		}
		List<String> commentQueryList = (List<String>) queryMap.get("commentQueryList");
		logger.debug("updateTable postService commentQueryList==>[{}] ",commentQueryList);
		if (commentQueryList.size() > 0) {
			for (String comment : commentQueryList) {
				logger.debug("updateTable postService comment==>[{}] ",comment);
				sqlEditorTableDao.createTable(comment);
			}
		}
		return 0;
	}
	
	public List<String> getPkList(){
		return pkList;
	}
	public void setUpdateTablePostDataList(List<SqlEditorTableVO> columnDataList) {
		this.postDataList = columnDataList;
	}
	
}

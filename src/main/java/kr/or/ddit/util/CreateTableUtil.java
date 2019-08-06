/**
 * 
 */
package kr.or.ddit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* CreateTableUtil.java
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
* 테이블 우클릭 후 테이블 생성할때 컬럼을 문자열로 반환해줄 클래스
* </pre>
*/
public class CreateTableUtil {

	private String pkCheck; 
	private String colName;
	private String dataType;
	private String colSize;
	private String nullCheck; 
	private String defaultVal;
	private String comment; 
	
	// pk체크한 컬럼의 이름을 담을 리스트
	List<String> pkColNameList = new ArrayList<String>();
	
	// 코멘트를 작성한 컬럼의 이름을 담을 리스트
	List<String> commentColNameList = new ArrayList<String>();
	
	// pk체크 한 수만큼 반복시킬 리스트
	List<String> pkCheckList = new ArrayList<String>();
	
	// 코멘트 내용을 담을 리스트
	List<String> commentList = new ArrayList<String>();
	
	// 코멘트 쿼리를 담을 리스트
	List<String> commentQueryList = new ArrayList<String>();
	
	// 쿼리를 담을 StringBuffer객체
	StringBuffer query = new StringBuffer();
	
	// 쿼리를 반환할 맵
	Map<String, Object> queryMap = new HashMap<String, Object>();
	
	/**
	* Method : jsonArraySplit
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : JSON 2차원 배열을 해당 인덱스에서 문자열의 키와 값이 든 배열로 분리
	*/
	private void jsonArraySplit(String[][] array, int idx) {
		
		// 분리한 배열을 담을 리스트 
		List<String[]> jsonSplitList = new ArrayList<String[]>(); 
		
		for (String[] arr : array) {
			for (int i = 0; i <= 6; i++) {
				jsonSplitList.add(arr[i].split(":"));
			}
		}
		splitVlaueList(jsonSplitList);
	}
	
	/**
	* Method : splitVlaueList
	* 작성자 : 이중석
	* 변경이력 :
	* @param jsonSplitList
	* Method 설명 : 값이 들어있는 인덱스 번호를 입력하여 값을 추출
	*/
	private void splitVlaueList (List<String[]> jsonSplitList) {
		
		// 추출한 값을 담을 리스트
		List<String> jsonValueList = new ArrayList<String>();
		
		// 값이 들어있는 인덱스 번호를 입력하여 값을 추출
		for (String[] jsonSplit : jsonSplitList) {
			jsonValueList.add(jsonSplit[1]);
		}
		jsonProcessing(jsonValueList);
	}
	
	/**
	* Method : jsonProcessing
	* 작성자 : 이중석
	* 변경이력 :
	* @param jsonValue
	* Method 설명 : 값에 붙어있는 세미콜론과 띄어쓰기 제거
	*/
	private void jsonProcessing(List<String> jsonValueList) {
		
		// json 배열에서 세미콜론과 띄어쓰기를 제거한 값을 담을 리스트
		List<String> jsonProcessingList = new ArrayList<String>();
		
		for (String jsonValue : jsonValueList) {
			jsonProcessingList.add(jsonValue.substring(2, jsonValue.length() -2));
		}
		pkCheck = jsonProcessingList.get(0);
		colName = jsonProcessingList.get(1);
		dataType = jsonProcessingList.get(2);
		colSize = jsonProcessingList.get(3);
		nullCheck = jsonProcessingList.get(4);
		defaultVal = jsonProcessingList.get(5);
		comment = jsonProcessingList.get(6);
	}
	
	/**
	* Method : dataTypeCheck
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : PK값으로 설정이 불가능한 데이터타입 검사 
	*/
	private void dataTypeCheck() {
		
		// PK값으로 설정이 불가능한 데이터타입 리스트
		List<String> noPkDataTypeList = DataTypeUtil.noPkDataType();
		
		for (int j = 0; j < noPkDataTypeList.size(); j++) {
			if(dataType.equals(noPkDataTypeList.get(j))) {
				pkCheck = "";
			}
		}
	}
	
	/**
	* Method : pkCheckList
	* 작성자 : 이중석
	* 변경이력 :
	* @param pkCheckList
	* @param pkColNameList
	* @param tableName
	* @param query
	* @return
	* Method 설명 : pk체크가 true인 컬럼을 해당 테이블에 생성하는 쿼리를 만든다.
	*/
	private String pkCheckList(List<String> pkCheckList, List<String> pkColNameList, 
			String tableName) {
		// pk체크한 컬럼이름 리스트를 이터레이터로 변환
		Iterator<String> it =  pkColNameList.iterator();
		StringBuffer pkQuery = new StringBuffer();
		// 첫번째 컬럼 이후부터 세미콜론을 찍기 위한 변수
		int cnt = 0;
		
		// pk리스트가 비어있지 않으면
		if (pkCheckList != null && pkCheckList.size() > 0) {
			
			// pk체크한 수만큼 반복
			for (int i = 0; i < pkCheckList.size(); i++) {
				
				// 반복문 중 pk를 생성할때 작성하는 쿼리 문법이 없으면 true
				if(!pkQuery.toString().contains("CONSTRAINT " + tableName + "_PK PRIMARY KEY")) {
					
					// pk생성 쿼리 작성
					pkQuery.insert(pkQuery.length(),",\tCONSTRAINT " + tableName + "_PK PRIMARY KEY\n\t(\n");
					
					// pk생성쿼리 소괄호 안의 pk설정한 컬럼이름을 반복 입력
					while(it.hasNext()) {
						
						// 두번째 컬럼 부터는 컬럼명 앞에 세미콜론이 붙는다.
						if(cnt == 0)
							pkQuery.insert(pkQuery.length(), "\t\t" +  it.next() + "\n");
						else
							pkQuery.insert(pkQuery.length(), ",\t\t" +  it.next() + "\n");
						cnt++;
					}
				}
			}
			// pk생성 끝
			pkQuery.append("\t)\n\t ENABLE");
		}
		return pkQuery.toString();
	}
	
	/**
	* Method : createCommentQuery
	* 작성자 : 이중석
	* 변경이력 :
	* @param commentList
	* @param account_id
	* @param tableName
	* @param commentColNameList
	* @return
	* Method 설명 : 컬럼당 코멘트를 생성하는 쿼리를 리스트화 하여 반환
	*/
	private List<String> createCommentQuery(List<String> commentList, String stNm, List<String> commentColNameList){
		List<String> commentQueryList = new ArrayList<String>();
		// 코멘트가 담겨있을때
		if(commentList != null && commentList.size() > 0) {
			String commentStr = "";
			// 코멘트의 횟수 만큼 반복하여 코멘트 작성
			for (int i = 0; i < commentList.size(); i++) {
				commentStr ="\nCOMMENT ON COLUMN " + stNm + "." + commentColNameList.get(i) + " IS " + "'" + commentList.get(i) + "'\n"; 
				commentQueryList.add(commentStr);
			}
		}
		return commentQueryList;
	}
	
	/**
	* Method : nullCheck
	* 작성자 : 이중석
	* 변경이력 :
	* @param pkColNameList
	* @return
	* Method 설명 : 테이블 수정 중 널체크를 했는지 확인해서 상황에 맞게 쿼리 반환
	*/
	private String nullCheck(List<String> pkColNameList) {
		
		for (int j = 0; j < pkColNameList.size(); j++) 
			if (pkColNameList.get(j).equals(colName)) 
				return " NOT NULL";
		
		if(nullCheck.equals("true")) 
			return " NOT NULL";
		
		return " NULL";
	}
	
	/**
	* Method : pkQueryProcessing
	* 작성자 : 이중석
	* 변경이력 :
	* @param pkColNameList
	* @param pkCheckList
	* @param stNm
	* @param tableName
	* @return
	* Method 설명 : 테이블 수정중 pk쿼리를 생성하여 반환
	*/
	private String pkQueryProcessing(List<String> pkColNameList, List<String> pkCheckList, String stNm, String tableName) {
		// pk체크한 컬럼이름 리스트를 이터레이터로 변환
		Iterator<String> it =  pkColNameList.iterator();
	
		// 첫번째 컬럼 이후부터 세미콜론을 찍기 위한 변수
		int cnt = 0;
		
		StringBuffer pkQuery = new StringBuffer();
		// pk체크한 수만큼 반복
		for (int i = 0; i < pkCheckList.size(); i++) {
			
			// 반복문 중 pk를 생성할때 작성하는 쿼리 문법이 없으면 true
			if(!pkQuery.toString().contains("ALTER TABLE "+ stNm +" \nADD CONSTRAINT " + tableName + "_PK PRIMARY KEY\n(\n")) {
				
				// pk생성 쿼리 작성
				pkQuery.append("ALTER TABLE "+ stNm +" \nADD CONSTRAINT " + tableName + "_PK PRIMARY KEY\n(\n");
				
				// pk생성쿼리 소괄호 안의 pk설정한 컬럼이름을 반복 입력
				while(it.hasNext()) {
					
					// 두번째 컬럼 부터는 컬럼명 앞에 세미콜론이 붙는다.
					if(cnt == 0)
						pkQuery.append("\t" +  it.next() + "\n");
					else
						pkQuery.append(",\t" +  it.next() + "\n");
					cnt++;
				}
			}
		}
		// pk생성 끝
		pkQuery.append(")\n ENABLE");
		
		return pkQuery.toString();
	}
	
	/**
	* Method : queryListClear
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 메서드 호출전 전역 변수를 초기화 해줌
	*/
	private void queryListClear() {
		pkColNameList.clear();
		commentColNameList.clear();
		pkCheckList.clear();
		commentList.clear();
		commentQueryList.clear();
		query.setLength(0);
	}
	
	/**
	* Method : getQuery
	* 작성자 : 이중석
	* 변경이력 :
	* @param array
	* @return
	* Method 설명 : 테이블을 생성할때 JSON 배열로 받아온 컬럼의 갯수와 형태에 맞게 쿼리를 문자열로 반환해주는 메서드
	*/
	protected Map<String, Object> getQuery(String[][] array, String tableSelect) {

		// 생성할 테이블 이름
		String tableName = array[0][7];
		
		// 스키마 아이디
		String account_id = array[0][8];
		
		// 스키마. 테이블명
		String stNm = account_id + "." + tableName;
		
		// 리스트 초기화
		queryListClear();
		
		if (tableSelect.equals("create")) {
			createTable(array, stNm, tableName);
		}else if(tableSelect.equals("update")) {
			updateTable(array, stNm, tableName);
		}

		// 컬럼당 코멘트를 생성하는 쿼리를 리스트화 
		commentQueryList = createCommentQuery(commentList, stNm, commentColNameList);
		queryMap.put("commentQueryList", commentQueryList);
		return queryMap;
	}
	
	/**
	* Method : updateTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param array
	* @param stNm
	* @param tableName
	* Method 설명 : 테이블 수정 
	*/
	private void updateTable(String[][] array, String stNm, String tableName) {
		List<String> queryList = new ArrayList<String>();
		// 스키마.테이블명
		queryMap.put("stNm", stNm);
		
		// ALTER TABLE SCHEMA.TABLENAME ADD(
		for (int i = 0; i < array.length; i++) {
			query.append("ALTER TABLE " + stNm + "\nADD (");
			
			jsonArraySplit(array, i);
			
			// PK값으로 설정이 불가능한 데이터타입 검사 
			dataTypeCheck();
			
			// ex ) user_id VARCHAR2
			query.append(colName+ " " + dataType);
			
			// ex ) user_id VARCHAR2(20)
			query.append(getcolSize(colSize, dataType));
			
			// 기본값을 입력했을 경우
			if(colDefaultCheck(dataType,defaultVal)) {
				query.append(" DEFAULT " + defaultVal);
			}
			// primary key를 체크했을때 pk를 체크한 컬럼의 이름과 pk체크 횟수를 리스트 리스트
			if(pkCheck.equals("true")) {
				pkCheckList.add(pkCheck);
				pkColNameList.add(colName);
				
			}
			
			// 널체크 여부
			if (pkColNameList.size() > 0) 
				query.append(nullCheck(pkColNameList));
			
			
			// 컬럼 뒤에 세미콜론
			if(i >= 0 && i < array.length - 1) 
				query.append("\t");
			
			// 코멘트를 남긴 컬럼의 이름과 코멘트의 내용을 담을 리스트
			if (!comment.equals("")) {
				commentList.add(comment);
				commentColNameList.add(colName);
			}
			
			// 코멘트를 제외한 테이블 생성 쿼리완성
			query.append(")");
			queryList.add(query.toString());
			query.setLength(0);
		}
		
		queryMap.put("pkColumnList", pkColNameList);
		
		// pk리스트가 비어있지 않으면
		if (pkCheckList != null && pkCheckList.size() > 0) 
			queryMap.put("pkQuery", pkQueryProcessing(pkColNameList, pkCheckList, stNm, tableName));
		
		queryMap.put("queryList", queryList);
	}
	
	/**
	* Method : createTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param array
	* @param stNm
	* @param tableName
	* Method 설명 : 테이블 생성 쿼리
	*/
	private void createTable(String[][] array, String stNm, String tableName) {
		
		// CREATE SCHEMA.TABLENAME (
		query.append("CREATE TABLE " + stNm + "\n(\n\t");
		for (int i = 0; i < array.length; i++) {
			
			// ajax JSON 배열로 온 문자열의 키와 값이 든 배열
			jsonArraySplit(array, i);
			
			// JSON으로 넘어온 문자열 배열에 붙어있는 중괄호 제거
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = array[i][j].replace("{", "");
				array[i][j] = array[i][j].replace("}", "");
			}
			
			// PK값으로 설정이 불가능한 데이터타입 검사 
			dataTypeCheck();
			
			// ex ) user_id VARCHAR2
			query.append(colName+ " " + dataType);
			
			// ex ) user_id VARCHAR2(20)
			query.append(getcolSize(colSize, dataType));
			
			// 기본값을 입력했을 경우
			if(colDefaultCheck(dataType,defaultVal)) {
				query.append(" DEFAULT " + defaultVal);
			}
			
			// 널체크 여부
			if(nullCheck.equals("true")) 
				query.append(" NOT NULL\n");
			else 
				query.append("\n");
			
			// 마지막 컬럼 전까지 컬럼 뒤에 반복하여 세미콜론
			if(i >= 0 && i < array.length - 1) 
				query.append(",\t");
			
			// primary key를 체크했을때 pk를 체크한 컬럼의 이름과 pk체크 횟수를 리스트 리스트
			if(pkCheck.equals("true")) {
				pkCheckList.add(pkCheck);
				pkColNameList.add(colName);
			}
			
			// 코멘트를 남긴 컬럼의 이름과 코멘트의 내용을 담을 리스트
			if (!comment.equals("")) {
				commentList.add(comment);
				commentColNameList.add(colName);
			}
		}
		
		// pk 생성 쿼리
		query.append(pkCheckList(pkCheckList, pkColNameList, tableName));
		
		// 코멘트를 제외한 테이블 생성 쿼리완성
		query.append("\n)");
		
		queryMap.put("query", query.toString());
		
	}
	
	/**
	* Method : getcolSize
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 : 컬럼 사이즈에 유효성 체크 후 반환
	*/
	private String getcolSize(String colSize, String dataType) {
		colSize = colSizeCheck(colSize);
		
		return getDefaultColSize(dataType, colSize);
	}
	
	/**
	* Method : colSizeCheck
	* 작성자 : 이중석
	* 변경이력 :
	* @param colSize
	* @return
	* Method 설명 : 컬럼 사이즈에 숫자가 아닌 값이 들어갔는지 체크 
	*/
	private String colSizeCheck(String colSize) {

		// 사이즈에 값을 입력하였을때 값이 숫자만 있지 않으면 공백 리턴
		if ((!colSize.equals("")) && (!colSize.matches("^[0-9]+"))) {
			return "";
		}
		return colSize;
	}
	
	/**
	* Method : getColSize
	* 작성자 : 이중석
	* 변경이력 :
	* @param dataType
	* @return
	* Method 설명 : 데이터 타입에 따른 기본 사이즈를 반환
	*/
	private String getDefaultColSize(String dataType, String colSize) {
		
		// 사이즈를 입력할 수없는 데이터 타입을 담은 리스트
		List<String> noSizeDataTypeList = DataTypeUtil.noSizeDataType();
		
		// 기본 사이즈가 20인 데이터 타입을 담은 리스트
		List<String> sizeDataTypeList = DataTypeUtil.sizeDataType();
		
		// 사이즈를 입력하지만 기본값을 설정하지 않아도 되는 데이터 타입을 담은 리스트 
		List<String> noDefaultDataTypeList = DataTypeUtil.noDefaultSize();

		for (int i = 0; i < noSizeDataTypeList.size(); i++) {
			// 생성하려는 테이블의 컬럼의 데이터 타입이 사이즈를 입력할수 없는 데이터 타입이면
			if (dataType.equals(noSizeDataTypeList.get(i))) {
				return "";
			}
		}
		if(colSize.equals("")) {
			for (int i = 0; i < sizeDataTypeList.size(); i++) {
				if (dataType.equals(sizeDataTypeList.get(i))) {
					return "(20)";
				}
			}
			for (int i = 0; i < noDefaultDataTypeList.size(); i++) {
				if(dataType.equals(noDefaultDataTypeList.get(i))) {
					return "";
				}
			}
		}
		return "(" + colSize + ")";
	}
	/**
	 * Method : colDefaultCheck
	 * 작성자 : 이중석
	 * 변경이력 :
	 * @param defaultVal
	 * @return
	 * Method 설명 : 컬럼 기본값에 숫자가 아닌 값이 들어갔는지 체크 
	 */
	private boolean colDefaultCheck(String dataType, String defaultVal) {
		
		// 크기가 없어야하는 데이터 타입 리스트
		List<String> noSizeDataTypeList = DataTypeUtil.noSizeDataType();
		
		for (int i = 0; i < noSizeDataTypeList.size(); i++) {
			if (dataType.equals(noSizeDataTypeList.get(i))) {
				return false;
			}
		}
		// 사이즈에 값을 입력하였을때 값이 숫자만 있지 않으면 false 리턴
		if ((!defaultVal.equals("")) && (!defaultVal.matches("^[0-9]+"))) {
			return false;
		}
		return true;
	}
	
}

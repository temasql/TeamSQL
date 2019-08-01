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

	private static final Logger logger = LoggerFactory.getLogger(CreateTableUtil.class);
	/**
	* Method : getQuery
	* 작성자 : 이중석
	* 변경이력 :
	* @param array
	* @return
	* Method 설명 : 테이블을 생성할때 JSON 배열로 받아온 컬럼의 갯수와 형태에 맞게 쿼리를 문자열로 반환해주는 메서드
	*/
	public static Map<String, Object> getQuery(String[][] array) {

		// private 메서드를 사용하기 위한 생성자
		CreateTableUtil ctu = new CreateTableUtil();
		
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
		
		// PK값으로 설정이 불가능한 데이터타입 리스트
		List<String> noPkDataTypeList = DataTypeUtil.noPkDataType();
		
		// 쿼리를 담을 StringBuffer객체
		StringBuffer query = new StringBuffer();

		// 생성할 테이블 이름
		String tableName = array[0][7];
		
		// 스키마 아이디
		String account_id = array[0][8];
		
		// CREATE SCHEMA.TABLENAME (
		query.append("CREATE TABLE " + account_id + "." + tableName + "\n(\n\t");
		for (int i = 0; i < array.length; i++) {
			
			// ajax JSON 배열로 온 문자열의 키와 값이 든 배열
			String[] pkSplit = array[i][0].split(":");
			String[] nameSplit = array[i][1].split(":");
			String[] dataTypeSplit = array[i][2].split(":");
			String[] sizeSplit = array[i][3].split(":");
			String[] nullCheckSplit = array[i][4].split(":");
			String[] defaultValSplit = array[i][5].split(":");
			String[] commentSplit = array[i][6].split(":");
			
			// 값이 들어있는 인덱스 번호를 입력하여 값을 추출
			String pks = pkSplit[1];
			String names = nameSplit[1];
			String dataTypes = dataTypeSplit[1];
			String sizes = sizeSplit[1];
			String nulls = nullCheckSplit[1];
			String defaults = defaultValSplit[1];
			String comments = commentSplit[1];
			
			// 값에 붙어있는 세미콜론과 띄어쓰기 제거
			pks = pks.substring(2, pks.length() -2);
			names = names.substring(2, names.length() -2);
			dataTypes = dataTypes.substring(2, dataTypes.length() -2);
			sizes = sizes.substring(2, sizes.length() -2);
			nulls = nulls.substring(2, nulls.length() -2);
			defaults = defaults.substring(2, defaults.length() -2);
			comments = comments.substring(2, comments.length() -2);
			
			// JSON으로 넘어온 문자열에 붙어있는 중괄호 제거
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = array[i][j].replace("{", "");
				array[i][j] = array[i][j].replace("}", "");
			}
			array[i][0] = pks;
			array[i][1] = names;
			array[i][2] = dataTypes;
			array[i][3] = sizes;
			array[i][4] = nulls;
			array[i][5] = defaults;
			array[i][6] = comments;
			
			String pkCheck = array[i][0];
			String colName = array[i][1];
			String dataType = array[i][2];
			String colSize = array[i][3];
			String nullCheck = array[i][4];
			String defaultVal = array[i][5];
			String comment = array[i][6];
			logger.debug("array{}",array);
			// PK값으로 설정이 불가능한 데이터타입 검사 
			for (int j = 0; j < noPkDataTypeList.size(); j++) {
				if(dataType.equals(noPkDataTypeList.get(j))) {
					pkCheck = "";
				}
			}
			
			// primary key를 체크하면 NOTNULL 체크도 함께 된다.
//			if(!pkCheck.equals("")) {
//				nullCheck = "true";
//			}
			
			// ex ) user_id VARCHAR2
			query.append(colName+ " " + dataType);
			
			// ex ) user_id VARCHAR2(20)
			query.append(ctu.getcolSize(colSize, dataType));
			
			// 기본값을 입력했을 경우
			if(!ctu.colDefaultCheck(dataType,defaultVal).equals("")) {
				query.append(" DEFAULT " + defaultVal);
			}
			
			// 널체크 여부
			if(nullCheck.equals("true")) {
				query.append(" NOT NULL\n");
			}else {
				query.append("\n");
			}
			// 컬럼 뒤에 세미콜론
			if(i >= 0 && i < array.length - 1) {
				query.append(",\t");
			}
			
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
		// pk체크한 컬럼이름 리스트를 이터레이터로 변환
		Iterator<String> it =  pkColNameList.iterator();
		
		// 첫번째 컬럼 이후부터 세미콜론을 찍기 위한 변수
		int cnt = 0;
		
		// pk리스트가 비어있지 않으면
		if (pkCheckList != null && pkCheckList.size() > 0) {
			
			// pk체크한 수만큼 반복
			for (int i = 0; i < pkCheckList.size(); i++) {
				
				// 반복문 중 pk를 생성할때 작성하는 쿼리 문법이 없으면 true
				if(!query.toString().contains("CONSTRAINT " + tableName + "_PK PRIMARY KEY")) {
					
					// pk생성 쿼리 작성
					query.insert(query.length(),",\tCONSTRAINT " + tableName + "_PK PRIMARY KEY\n\t(\n");
					
					// pk생성쿼리 소괄호 안의 pk설정한 컬럼이름을 반복 입력
					while(it.hasNext()) {
						
						// 두번째 컬럼 부터는 컬럼명 앞에 세미콜론이 붙는다.
						if(cnt == 0)
							query.insert(query.length(), "\t\t" +  it.next() + "\n");
						else
							query.insert(query.length(), ",\t\t" +  it.next() + "\n");
						cnt++;
					}
				}
			}
			// pk생성 끝
			query.append("\t)\n\t ENABLE");
		}
		// 코멘트를 제외한 테이블 생성 쿼리완성
		query.append("\n)");

		// 코멘트가 담겨있을때
		if(commentList != null && commentList.size() > 0) {
			String commentStr = "";
			// 코멘트의 횟수 만큼 반복하여 코멘트 작성
			for (int i = 0; i < commentList.size(); i++) {
				commentStr ="\nCOMMENT ON COLUMN " + account_id + "." + tableName + "." + commentColNameList.get(i) + " IS " + "'" + commentList.get(i) + "'\n"; 
				commentQueryList.add(commentStr);
			}
		}
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("query", query.toString());
		queryMap.put("commentQueryList", commentQueryList);
		// String 타입으로 반환
		return queryMap;
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
	private String colDefaultCheck(String dataType, String defaultVal) {
		List<String> noSizeDataTypeList = DataTypeUtil.noSizeDataType();
		for (int i = 0; i < noSizeDataTypeList.size(); i++) {
			if (dataType.equals(noSizeDataTypeList.get(i))) {
				return "";
			}
		}
		// 사이즈에 값을 입력하였을때 값이 숫자만 있지 않으면 공백 리턴
		if ((!defaultVal.equals("")) && (!defaultVal.matches("^[0-9]+"))) {
			return "";
		}
		return defaultVal;
	}
	
}

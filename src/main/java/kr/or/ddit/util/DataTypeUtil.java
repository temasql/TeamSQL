/**
 * 
 */
package kr.or.ddit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* DataTypeUtil.java
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
* SQL 에디터 테이블 생성시 컬럼을 추가하고 컬럼의 데이터 타입을 동적으로 리스트화 시킬 클래스
* </pre>
*/
public class DataTypeUtil {

	/**
	* Method : tableDataType
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 : 컬럼의 데이터 유형이 스트링 리스트로 들어있다
	*/
	public static List<String> tableDataType() {
		
		List<String> dataTypeList = new ArrayList<String>();
		dataTypeList.add("VARCHAR2");
		dataTypeList.add("NUMBER");
		dataTypeList.add("DATE");
		dataTypeList.add("CLOB");
		dataTypeList.add("BLOB");
		dataTypeList.add("BFILE");
		dataTypeList.add("BINARY_DOUBLE");
		dataTypeList.add("BINARY_FLOAT");
		dataTypeList.add("CHAR");
		dataTypeList.add("CHAR VARYING");
		dataTypeList.add("CHARACTER");
		dataTypeList.add("CHARACTER VARYING");
		dataTypeList.add("DEC");
		dataTypeList.add("DECIMAL");
		dataTypeList.add("DOUBLE PRECISION");
		dataTypeList.add("FLOAT");
		dataTypeList.add("INT");
		dataTypeList.add("INTEGER");
		dataTypeList.add("INTERVAR DAY");
		dataTypeList.add("INTERVAR YEAR");
		dataTypeList.add("LONG");
		dataTypeList.add("LONG RAW");
		dataTypeList.add("LONG VARCHAR");
		dataTypeList.add("NATIONAL CHAR");
		dataTypeList.add("NATIONAL CHAR VARYING");
		dataTypeList.add("NATIONAL CHARACTER");
		dataTypeList.add("NATIONAL CHARACTER VARYING");
		dataTypeList.add("NCHAR");
		dataTypeList.add("NCHAR VARYING");
		dataTypeList.add("NCLOB");
		dataTypeList.add("NUMERIC");
		dataTypeList.add("NVARCHAR2");
		dataTypeList.add("RAW");
		dataTypeList.add("REAL");
		dataTypeList.add("ROWID");
		dataTypeList.add("SMALLINT");
		dataTypeList.add("TIMESTAMP");
		dataTypeList.add("UROWID");
		dataTypeList.add("VARCHAR");
		return dataTypeList;
	}
	
	/**
	* Method : noSizeDataType
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 : 사이즈를 입력할수 없는 데이터 타입
	*/
	public static List<String> noSizeDataType() {
		List<String> noSizeDataTypeList = new ArrayList<String>();
		noSizeDataTypeList.add("DATE");
		noSizeDataTypeList.add("CLOB");
		noSizeDataTypeList.add("BLOB");
		noSizeDataTypeList.add("BFILE");
		noSizeDataTypeList.add("BINARY_DOUBLE");
		noSizeDataTypeList.add("BINARY_FLOAT");
		noSizeDataTypeList.add("CHAR");
		noSizeDataTypeList.add("DOUBLE PRECISION");
		noSizeDataTypeList.add("INT");
		noSizeDataTypeList.add("INTEGER");
		noSizeDataTypeList.add("LONG");
		noSizeDataTypeList.add("LONG RAW");
		noSizeDataTypeList.add("LONG VARCHAR");
		noSizeDataTypeList.add("NCLOB");
		return noSizeDataTypeList;
	}
	
	/**
	 * Method : noSizeDataType
	 * 작성자 : 이중석
	 * 변경이력 :
	 * @return
	 * Method 설명 : 데이터를 insert할때 싱글쿼테이션으로 감싸줘야하는 데이터타입
	 */
	public static Map<String, Object> stringDataType() {
		Map<String, Object> noSizeDataTypeMap = new HashMap<String, Object>();
		noSizeDataTypeMap.put("DATE", "DATE");
		noSizeDataTypeMap.put("CLOB", "CLOB");
		noSizeDataTypeMap.put("BLOB", "BLOB");
		noSizeDataTypeMap.put("BFILE", "BFILE");
		noSizeDataTypeMap.put("BINARY_DOUBLE","BINARY_DOUBLE");
		noSizeDataTypeMap.put("BINARY_FLOAT","BINARY_FLOAT");
		noSizeDataTypeMap.put("CHAR","CHAR");
		noSizeDataTypeMap.put("DOUBLE PRECISION","DOUBLE PRECISION");
		noSizeDataTypeMap.put("INT","INT");
		noSizeDataTypeMap.put("INTEGER","INTEGER");
		noSizeDataTypeMap.put("LONG","LONG");
		noSizeDataTypeMap.put("LONG RAW","LONG RAW");
		noSizeDataTypeMap.put("LONG VARCHAR","LONG VARCHAR");
		noSizeDataTypeMap.put("NCLOB","NCLOB");
		noSizeDataTypeMap.put("VARCHAR2","VARCHAR2");
		return noSizeDataTypeMap;
	}
	
	/**
	* Method : sizeDataType
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 기본 사이즈가 20인 데이터 타입
	*/
	public static List<String> sizeDataType() {
		List<String> sizeDataType = new ArrayList<String>();
		sizeDataType.add("CHAR VARYING");
		sizeDataType.add("CHARACTER VARYING");
		sizeDataType.add("NATIONAL CHAR VARYING");
		sizeDataType.add("NATIONAL CHARACTER VARYING");
		sizeDataType.add("NCHAR VARYING");
		sizeDataType.add("RAW");
		sizeDataType.add("REAL");
		sizeDataType.add("ROWID");
		sizeDataType.add("SMALLINT");
		sizeDataType.add("TIMESTAMP");
		sizeDataType.add("VARCHAR");
		sizeDataType.add("VARCHAR2");
		return sizeDataType;
	}
	
	/**
	* Method : noDefaultSize
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 : 사이즈를 입력할 수 있지만 기본값 설정 안하는 데이터 타입
	*/
	public static List<String> noDefaultSize(){
		List<String> noDefaultDataTypeList = new ArrayList<String>();
		noDefaultDataTypeList.add("NUMBER");
		noDefaultDataTypeList.add("CHAR");
		noDefaultDataTypeList.add("CHARACTER");
		noDefaultDataTypeList.add("DEC");
		noDefaultDataTypeList.add("DECIMAL");
		noDefaultDataTypeList.add("FLOAT");
		noDefaultDataTypeList.add("INTERVAR DAY");
		noDefaultDataTypeList.add("INTERVAR YEAR");
		noDefaultDataTypeList.add("NATIONAL CHAR");
		noDefaultDataTypeList.add("NATIONAL CHARACTER");
		noDefaultDataTypeList.add("NCHAR");
		noDefaultDataTypeList.add("NUMERIC");
		noDefaultDataTypeList.add("UROWID");
		return noDefaultDataTypeList;
	}
	
	/**
	* Method : noPkDataType
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 : PK값으로 설정이 불가능한 데이터타입 
	*/
	public static List<String> noPkDataType(){
		List<String> noPkDataTypeList = new ArrayList<String>();
		noPkDataTypeList.add("CLOB");
		noPkDataTypeList.add("BLOB");
		noPkDataTypeList.add("BFILE");
		noPkDataTypeList.add("LONG");
		noPkDataTypeList.add("LONG RAW");
		noPkDataTypeList.add("LONG VARCHAR");
		return noPkDataTypeList;
	}
	
}

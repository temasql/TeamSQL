/**
 * 
 */
package kr.or.ddit.util;

import java.util.ArrayList;
import java.util.List;

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
		dataTypeList.add("SMALLINT");
		dataTypeList.add("TIMESTAMP");
		dataTypeList.add("UROWID");
		dataTypeList.add("VARCHAR");
		return dataTypeList;
	}
}

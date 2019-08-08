/**
 * 
 */
package kr.or.ddit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TableExportUtil.java
 *
 * @author 이중석
 * @version 1.0
 * @see
 *
 *      <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* 이중석 최초 생성
 * 
 *      </pre>
 */
public class TableExportUtil {

	/**
	 * Method : getNoSizeColList 
	 * 작성자 : 이중석 
	 * 변경이력 :
	 * 
	 * @return Method 설명 : 해당 테이블의 컬럼중 크기가 들어가면 안되는 컬럼의 이름을 가진 리스트를 반환
	 */
	public static List<String> getNoSizeColList(List<List<String>> colNameNDataTypeList, List<String> columnNameList) {
		
		// 사이즈가 없는 데이터 타입 리스트
		Map<String, Object> stringDataType = DataTypeUtil.stringDataType();
		
		// 테이블의 컬럼중 크기가 들어가면 안되는 컬럼 리스트
		List<String> nosizeColList = new ArrayList<String>();
		
		// 컬럼의 이름을 담을 변수
		String colName = "";

		// 데이터 타입을 담을 변수
		String dataType = "";

		// 컬럼의 수만큼 반복
		for (String columnName : columnNameList) {

			for (int i = 1; i < colNameNDataTypeList.size(); i++) {

				// 컬럼의 이름이 들어있는 인덱스 번호
				colName = colNameNDataTypeList.get(i).get(0);

				// 데이터 타입이 들어있는 인덱스 번호
				dataType = colNameNDataTypeList.get(i).get(1);

				// 데이타 타입 맵에 있는 컬럼의 이름과 컬럼정보 리스트의 컬럼의 이름이 같으면
				if (columnName.equals(colName)) {
					
					String dt = (String) stringDataType.get(dataType);
					if (dt != null) {
						nosizeColList.add(columnName);
					}
				}
			}
		}
		return nosizeColList;
	}
	/**
	* Method : getInsertQueryList
	* 작성자 : 이중석
	* 변경이력 :
	* @param stNm
	* @param dataList
	* @param columnNameList
	* @param nosizeColList
	* @return
	* Method 설명 : 테이블에 대한 데이터 insert 쿼리 리스트
	*/
	public static List<String> getInsertQueryList(String stNm, List<List<String>> dataList,
			List<String> columnNameList, List<String> nosizeColList){
		
		// insert 쿼리를 담기 위한 객체
		StringBuffer insertQuery = new StringBuffer();
		
		// row수만큼 insert쿼리를 담을 리스트
		List<String> insertDataList = new ArrayList<String>();
		
		// 전체 row수만큼 반복
		for (int i = 1; i < dataList.size(); i++) {
			insertQuery.append("INSERT INTO " + stNm + " VALUES (");
			// 하나의 row를 담은 리스트
			List<String> colDataList = dataList.get(i);
			
			Map<String, Object> nosizeColMap = new HashMap<String, Object>();
			for (String noSizeCol : nosizeColList) {
				nosizeColMap.put(noSizeCol, noSizeCol);
			}
			
			// row에서 컬럼당 데이터를 반복하여 조회
			for (int j = 0; j < colDataList.size(); j++) {
				
				if (j == 0) {
					insertQuery.append(" '" + colDataList.get(j) + "'");
				}else {
					insertQuery.append(", '" + colDataList.get(j) + "'");
				}
				
				for (String columnName : columnNameList) {
					
					String nullStr = "'null'";
					if (insertQuery.toString().contains("'null'")) {
						insertQuery.replace(insertQuery.indexOf(nullStr), insertQuery.indexOf(nullStr) + nullStr.length() + 1, "NULL");
						break;
					}
					if(nosizeColMap.get(columnName) == null) {
						int start = insertQuery.indexOf("'" + colDataList.get(j) + "'" );
						String endStr = "'" + colDataList.get(j) + "'";
						int end = start + endStr.length() + 1;
						insertQuery.replace(start , end , colDataList.get(j));
						break;
					}
				}
			}
			
			// insert 쿼리가 끝나고 소괄호를 닫음
			insertQuery.append(" );");
			
			// 하나의 insert쿼리를 리스트에 담는다.
			insertDataList.add(insertQuery.toString());
			
			// 객체 비우기
			insertQuery.setLength(0);
		}
		return insertDataList;
	}
}

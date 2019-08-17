package kr.or.ddit.sqlEditorIndex.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import kr.or.ddit.sqlEditorIndex.model.IndexColVO;
import kr.or.ddit.sqlEditorIndex.model.IndexDetailVO;

/**
 * 
* ISqlEditorIndexDao.java
*
* @author 강호길
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* 강호길 최초 생성
*
* </pre>
 */
public interface ISqlEditorIndexDao {

	/**
	 * 
	* Method : indexTable
	* 작성자 : 강호길
	* 변경이력 :
	* @param index_owner
	* @return
	* Method 설명 : 인덱스 테이블 조회
	 */
	List<String> indexTable(String index_owner);
	
	/**
	 * 
	* Method : tableColumn
	* 작성자 : 강호길
	* 변경이력 :
	* @param table_name
	* @return
	* Method 설명 : 인덱스 컬럼 조회
	 */
	List<String> tableColumn(Map<String, String> map);
	
	/**
	 * 
	* Method : createIndex
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 인덱스 생성
	 */
	boolean createIndex (String query,Connection conn);
	
	/**
	 * 
	 * Method : updateIndex
	 * 작성자 : 강호길
	 * 변경이력 :
	 * @param query
	 * @return
	 * Method 설명 : 인덱스 편집
	 */
	boolean updateIndex (String deleteQuery,Connection conn);
	
	/**
	 * 
	* Method : indexQuery
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 인덱스 쿼리 조회	
	 */
	String indexQuery(Map<String, String> map);
	
	/**
	 * 
	* Method : indexCol
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 인덱스 열 조회
	 */
	List<IndexColVO> indexCol(Map<String, String> map);
	
	/**
	 * 
	* Method : detailIndex
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 인덱스 세부 정보 조회
	 */
	IndexDetailVO indexDetail(Map<String, String> map);
	
	/**
	 * 
	* Method : indexDelete
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 인덱스 삭제
	 */
	int indexDelete(String query);
	
	/**
	 * 
	* Method : beforeIndexOwner
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 편집 전 데이터(계정명, 테이블명, 인덱스명, 정렬)
	 */
	List<IndexColVO> beforeIndexOwner(Map<String, String> map);
	
	
	/**
	 * 
	* Method : beforeIndexType
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 편집 전 데이터("인덱스 유형")
	 */
	String beforeIndexType(Map<String, String> map);
}

package kr.or.ddit.sqlEditorIndex.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.sqlEditorIndex.dao.ISqlEditorIndexDao;
import kr.or.ddit.sqlEditorIndex.model.IndexColVO;
import kr.or.ddit.sqlEditorIndex.model.IndexDetailVO;

@Service
public class SqlEditorIndexService implements ISqlEditorIndexService{

	@Resource(name = "sqlEditorIndexDao")
	private ISqlEditorIndexDao sqlEditorIndexDao;
	
	/**
	 * 
	* Method : indexQuery
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 인덱스 쿼리 조회
	 */
	@Override
	public String indexQuery(Map<String, String> map) {
		
		return sqlEditorIndexDao.indexQuery(map);
	}
	
	/**
	 * 
	* Method : indexCol
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 인덱스 열 조회
	 */
	@Override
	public List<IndexColVO> indexCol(Map<String, String> map) {
		return sqlEditorIndexDao.indexCol(map);
	}

	/**
	 * 
	* Method : indexDetail
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 인덱스 세부 정보 조회
	 */
	@Override
	public IndexDetailVO indexDetail(Map<String, String> map) {
		return sqlEditorIndexDao.indexDetail(map);
	}

	/**
	 * 
	* Method : indexDelete
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 인덱스 삭제
	 */
	@Override
	public int indexDelete(String query) {
		return sqlEditorIndexDao.indexDelete(query);
	}

	/**
	 * 
	* Method : indexTable
	* 작성자 : 강호길
	* 변경이력 :
	* @param index_owner
	* @return
	* Method 설명 : 인덱스 테이블 조회
	 */
	@Override
	public List<String> indexTable(String index_owner) {
		return sqlEditorIndexDao.indexTable(index_owner);
	}

	/**
	 * 
	* Method : tableColumn
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 테이블 컬럼 조회
	 */
	@Override
	public List<String> tableColumn(Map<String, String> map) {
		return sqlEditorIndexDao.tableColumn(map);
	}

	/**
	 * 
	* Method : createIndex
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 인덱스 생성
	 */
	@Override
	public int createIndex(String query, Connection conn) {
		boolean result = sqlEditorIndexDao.createIndex(query,conn);
		return 0; 
	}
	
	/**
	 * 
	 * Method : updateIndex
	 * 작성자 : 강호길
	 * 변경이력 :
	 * @param query
	 * @return
	 * Method 설명 : 인덱스 편집
	 */
	@Override
	public int updateIndex(String deleteQuery, Connection conn) {
		boolean result = sqlEditorIndexDao.updateIndex(deleteQuery, conn);
		return 0; 
	}
	
	/**
	 * 
	* Method : beforeIndexOwner
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 편집 전 데이터(계정명, 테이블명, 인덱스명, 정렬)
	 */
	@Override
	public List<IndexColVO> beforeIndexOwner(Map<String, String> map) {
		return sqlEditorIndexDao.beforeIndexOwner(map);
	}

	/**
	 * 
	* Method : beforeIndexType
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 편집 전 데이터("인덱스 유형")
	 */
	@Override
	public String beforeIndexType(Map<String, String> map) {
		return sqlEditorIndexDao.beforeIndexType(map);
	}

}

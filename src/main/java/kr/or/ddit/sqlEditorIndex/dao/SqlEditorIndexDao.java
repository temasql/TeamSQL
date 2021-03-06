package kr.or.ddit.sqlEditorIndex.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.sqlEditorIndex.model.ColCheckVO;
import kr.or.ddit.sqlEditorIndex.model.IndexColVO;
import kr.or.ddit.sqlEditorIndex.model.IndexDetailVO;

@Repository
public class SqlEditorIndexDao implements ISqlEditorIndexDao {
	private static final Logger logger = LoggerFactory.getLogger(SqlEditorIndexDao.class);

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	 * 
	 * Method : indexQuery 작성자 : 강호길 변경이력 :
	 * 
	 * @param map
	 * @return Method 설명 : 인덱스 쿼리 조회
	 */
	@Override
	public String indexQuery(Map<String, String> map) {
		return sqlSession.selectOne("sqlEditorIndex.indexQuery", map);
	}

	/**
	 * 
	 * Method : selectIndex 작성자 : 강호길 변경이력 :
	 * 
	 * @param map
	 * @return Method 설명 : 인덱스 열 조회
	 */
	@Override
	public List<IndexColVO> indexCol(Map<String, String> map) {
		return sqlSession.selectList("sqlEditorIndex.indexCol", map);
	}

	/**
	 * 
	 * Method : detailIndex 작성자 : 강호길 변경이력 :
	 * 
	 * @param map
	 * @return Method 설명 : 인덱스 세부 정보 조회
	 */
	@Override
	public IndexDetailVO indexDetail(Map<String, String> map) {
		return sqlSession.selectOne("sqlEditorIndex.indexDetail", map);
	}

	/**
	 * 
	 * Method : indexDelete 작성자 : 강호길 변경이력 :
	 * 
	 * @param query
	 * @return Method 설명 : 인덱스 삭제
	 */
	@Override
	public int indexDelete(String query) {
		int result = -99;
		try {
			result = sqlSession.delete("sqlEditorIndex.indexDelete", query);
		} catch (Exception e) {
			result = -99;
		}
		return result;

	}

	/**
	 * 
	 * Method : indexTable 작성자 : 강호길 변경이력 :
	 * 
	 * @param index_owner
	 * @return Method 설명 : 인덱스 테이블 조회
	 */
	@Override
	public List<String> indexTable(String index_owner) {
		return sqlSession.selectList("sqlEditorIndex.indexTable", index_owner);
	}

	/**
	 * 
	 * Method : tableColumn 작성자 : 강호길 변경이력 :
	 * 
	 * @param table_name
	 * @return Method 설명 : 테이블 컬럼 조회
	 */
	@Override
	public List<String> tableColumn(Map<String, String> map) {
		return sqlSession.selectList("sqlEditorIndex.tableColumn", map);
	}

	/**
	 * 
	 * Method : createIndex 작성자 : 강호길 변경이력 :
	 * 
	 * @param query
	 * @return Method 설명 : 인덱스 생성
	 */
	@Override
	public boolean createIndex(String query, Connection conn) {
		Statement stmt = null;

		boolean result = false;

		try {
			stmt = conn.createStatement();
			result = stmt.execute(query);
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
			try {
				throw e;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
		}

		return result;
	}

	/**
	 * 
	 * Method : beforeIndexOwner 작성자 : 강호길 변경이력 :
	 * 
	 * @param map
	 * @return Method 설명 : 편집 전 데이터(계정명, 테이블명, 인덱스명, 정렬)
	 */
	@Override
	public List<IndexColVO> beforeIndexOwner(Map<String, String> map) {
		return sqlSession.selectList("sqlEditorIndex.beforeIndexOwner", map);
	}

	/**
	 * 
	 * Method : beforeIndexType 작성자 : 강호길 변경이력 :
	 * 
	 * @param map
	 * @return Method 설명 : 편집 전 데이터("인덱스 유형")
	 */
	@Override
	public String beforeIndexType(Map<String, String> map) {
		return sqlSession.selectOne("sqlEditorIndex.beforeIndexType", map);
	}

	/**
	 * 
	 * Method : updateIndex 작성자 : 강호길 변경이력 :
	 * 
	 * @param query
	 * @param conn
	 * @return Method 설명 : 인덱스 편집
	 */
	@Override
	public boolean updateIndex(String deleteQuery, Connection conn) {
		Statement stmt = null;

		boolean result = false;

		try {
			stmt = conn.createStatement();
			result = stmt.execute(deleteQuery);
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		} finally {
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
		}

		return result;
	}

	
	/**
	 * 
	* Method : indexName
	* 작성자 : 강호길
	* 변경이력 :
	* @param owner
	* @param index_name
	* @return
	* Method 설명 : 인덱스명 중복 검사
	 */
	@Override
	public String indexName(Map<String, String> map) {
		return sqlSession.selectOne("sqlEditorIndex.indexName", map);
	}

	/**
	 * 
	 * Method : nameList
	 * 작성자 : 강호길
	 * 변경이력 :
	 * @param map
	 * @return
	 * Method 설명 : 테이블 내 인덱스명 조회 리스트
	 */
	@Override
	public List<String> nameList(Map<String, String> map) {
		return sqlSession.selectList("sqlEditorIndex.nameList",map);
	}
	
	/**
	 * 
	* Method : colCheck
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 인덱스 컬럼 중복검사
	 */
	@Override
	public List<ColCheckVO> colCheck(Map<String, String> map) {
		return sqlSession.selectList("sqlEditorIndex.colCheck", map);
	}

	

}

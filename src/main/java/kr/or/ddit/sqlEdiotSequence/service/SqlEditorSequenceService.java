/**
 * 
 */
package kr.or.ddit.sqlEdiotSequence.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.sqlEdiotSequence.dao.ISqlEditorSequenceDao;
import kr.or.ddit.sqlEdiotSequence.model.DetailSeqVO;
import kr.or.ddit.sqlEdiotSequence.model.SelectSeqVO;

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
public class SqlEditorSequenceService implements ISqlEditorSequenceService {

	
	@Resource(name = "sqlEditorSequenceDao")
	private ISqlEditorSequenceDao sqlEditorSequenceDao;
	
	/**
	* Method : createTable
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @return 시퀀스 정보
	* Method 설명 : 테이블패키지 우클릭 후 테이블 생성 
	*/
	@Override
	public int createSequence(String query) {
		
		int createSeq =sqlEditorSequenceDao.createSequence(query);
		
		return createSeq;
	}

	/**
	 * 
	* Method : selectSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param seqVO
	* @return 시퀀스 정보
	* Method 설명 : 시퀀스 조회
	 */
	@Override
	public String selectSequence(SelectSeqVO seqVO) {
		String selectSeq = sqlEditorSequenceDao.selectSequence(seqVO);
		
		return selectSeq ;
	}

	/**
	 * 
	* Method : selectSequenceTable
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 시퀀스 세부 정보 조회
	 */
	@Override
	public DetailSeqVO selectSequenceTable(Map<String, String> map) {
		DetailSeqVO seqTable = sqlEditorSequenceDao.selectSequenceTable(map);
		return seqTable;
	}
	
	/**
	 * 
	* Method : beforeSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 시퀀스 편집 전 데이터 조회
	 */
	@Override
	public SelectSeqVO beforeSequence(Map<String, String> map) {
		SelectSeqVO seqVO = sqlEditorSequenceDao.beforeSequence(map);
		return seqVO;
	}

	/**
	 * 
	* Method : updateSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 시퀀스 편집
	 */
	@Override
	public int updateSequence(String query) {
		int updateCnt = sqlEditorSequenceDao.updateSequence(query);
		return updateCnt;
	}
	
	/**
	 * 
	* Method : deleteSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 시퀀스 삭제
	 */
	@Override
	public int deleteSequence(String query) {
		
		int deleteCnt = sqlEditorSequenceDao.deleteSequence(query);
		return deleteCnt;
	}

}

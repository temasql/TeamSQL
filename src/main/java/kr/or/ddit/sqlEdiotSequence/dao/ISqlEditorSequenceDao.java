/**
 * 
 */
package kr.or.ddit.sqlEdiotSequence.dao;

import java.util.Map;

import kr.or.ddit.sqlEdiotSequence.model.DetailSeqVO;
import kr.or.ddit.sqlEdiotSequence.model.SelectSeqVO;

/**
 * 
* ISqlEditorSequenceDao.java
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
public interface ISqlEditorSequenceDao {

	/**
	 * 
	* Method : createSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 우클릭 이벤트 시퀀스 생성
	 */
	int createSequence(String query);
	
	/**
	 * 
	* Method : selectSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param seqVO
	* @return
	* Method 설명 : 시퀀스 조회
	 */
	String selectSequence(SelectSeqVO seqVO);
	
	/**
	 * 
	* Method : selectSequenceTable
	* 작성자 : 강호길
	* 변경이력 :
	* @param seqVO
	* @return
	* Method 설명 : 시퀀스 세부정보 조회
	 */
	DetailSeqVO selectSequenceTable(Map<String, String> map);
	
	
	/**
	 * 
	* Method : beforeSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 시퀀스 편집 전 데이터
	 */
	SelectSeqVO beforeSequence(Map<String, String> map);
	
	/**
	 * 
	* Method : updateSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 시퀀스 편집
	 */
	int updateSequence(String query);
	
	/**
	 * 
	* Method : deleteSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param seqVO
	* @return
	* Method 설명 : 시퀀스 삭제
	 */
	int deleteSequence(String query);
	
}

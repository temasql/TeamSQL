/**
 * 
 */
package kr.or.ddit.sqlEdiotSequence.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.sqlEdiotSequence.dao.ISqlEditorSequenceDao;

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
	* @return
	* Method 설명 : 테이블패키지 우클릭 후 테이블 생성 
	*/
	@Override
	public int createSequence(String query) {
		
		int createSeq =sqlEditorSequenceDao.createSequence(query);
		
		return createSeq;
	}
	

}

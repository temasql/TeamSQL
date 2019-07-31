/**
 * 
 */
package kr.or.ddit.sqlEdiotTable.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.sqlEdiotTable.dao.ISqlEditorTableDao;
import kr.or.ddit.sqlEdiotTable.dao.SqlEditorTableDao;
import kr.or.ddit.util.CreateTableUtil;

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
public class SqlEditorTableService implements ISqlEditorTableService {

	
	@Resource(name = "sqlEditorTableDao")
	private ISqlEditorTableDao sqlEditorTableDao;
	/**
	* Method : createTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 :
	*/
	@Override
	public int createTable(String[][] array) {
		Map<String, Object> queryMap = new CreateTableUtil().getQuery(array);
		String createTableStr = (String) queryMap.get("query");
		sqlEditorTableDao.createTable(createTableStr);
		List<String> commentQueryList = (List<String>) queryMap.get("commentQueryList");
		if (commentQueryList.size() > 0) {
			for (String comment : commentQueryList) {
				sqlEditorTableDao.createTable(comment);
			}
		}
		return 0;
	}

}

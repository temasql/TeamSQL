/**
 * 
 */
package kr.or.ddit.sqlEdiotView.service;

import java.sql.Connection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.sqlEdiotView.dao.ISqlEditorViewDao;
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
public class SqlEditorViewService extends CreateTableUtil implements ISqlEditorViewService {

	@Resource(name = "sqlEditorViewDao")
	private ISqlEditorViewDao sqlEditorViewDao;

	/**
	* Method : createView
	* 작성자 : 이중석
	* 변경이력 :
	* @param sc_id
	* @param view_name
	* @param viewQuery
	* @return
	* Method 설명 :
	*/
	@Override
	public int createView(String view_name, String viewQuery,  Connection conn) {
		
		StringBuffer createQuery = new StringBuffer();
		createQuery.append("CREATE VIEW " + view_name + " AS " + viewQuery);
		boolean result = sqlEditorViewDao.createView(createQuery.toString(), conn);
		return 0;
	}
	/**
	* Method : deleteView
	* 작성자 : 이중석
	* 변경이력 :
	* @param view_name
	* @param conn
	* @return
	* Method 설명 :
	*/
	@Override
	public int deleteView(String view_name, Connection conn) {
		
		String dropQuery = "DROP VIEW " + view_name; 
		boolean result = sqlEditorViewDao.deleteView(dropQuery, conn);
		return 0;
	}
	/**
	* Method : updateView
	* 작성자 : 이중석
	* 변경이력 :
	* @param view_name
	* @param conn
	* @return
	* Method 설명 :
	*/
	@Override
	public String updateView(String view_name, Connection conn) {
		return sqlEditorViewDao.updateView(view_name, conn);
	}
	/**
	* Method : updateViewPost
	* 작성자 : 이중석
	* 변경이력 :
	* @param oldVN
	* @param view_name
	* @param conn
	* @return
	* Method 설명 :
	*/
	@Override
	public String updateViewPost(String oldVN, String view_name,
			String viewQuery ,Connection conn) {
		StringBuffer sbf = new StringBuffer();
		boolean dropCheck = false;
		if (!oldVN.equals(view_name)) {
			deleteView(oldVN, conn);
			dropCheck = true;
		}
		if (dropCheck) {
			createView(view_name, viewQuery, conn);
		}else {
			sbf.append("CREATE OR REPLACE VIEW " + view_name + " AS " + viewQuery);
			sqlEditorViewDao.createView(sbf.toString(), conn);
		}
		return null;
	}

	
}

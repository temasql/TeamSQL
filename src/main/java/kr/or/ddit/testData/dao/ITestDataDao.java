/**
 * 
 */
package kr.or.ddit.testData.dao;

import java.util.List;
import java.util.Map;

/**
 * 
* ISqlEditorTriggerDao.java
*
* @author 김범휘
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자	      수정내용
* --------  ------------------------
* PC06      최초 생성
*
* </pre>
 */
public interface ITestDataDao {
	
	List<String> getTestData(Map<String, Object> map);
	
	List<String> getNameData();
	
	List<String> getPhoneData();
	
	List<String> getDateData();
	
	List<String> getCountryData();
	
	List<String> getEmailData();
	
	List<String> getCityData();
	
}

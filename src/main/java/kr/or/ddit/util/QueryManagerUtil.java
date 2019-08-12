/**
 * 
 */
package kr.or.ddit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* QueryManagerUtil.java
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
public class QueryManagerUtil {
	private static final String LEFT_PROCESSING = "좌변을 가공한 쿼리입니다 \n\n 인덱스 컬럼은 비교되기 전 변형이 일어나면 인덱스를 사용할 수 없습니다.\n\n 해당 쿼리를 \n\n";
	/**
	* Method : managerment
	* 작성자 : 이중석
	* 변경이력 :
	* @param dragText
	* @return
	* Method 설명 : 쿼리를 분석하여 가공해서 반환해주는 메서드
	*/
	public static Map<String, Object> managerment(String dragText) {
		// 쿼리를 모두 대문자로 변환한다.
		dragText = dragText.toUpperCase();
		
		// 쿼리의 공백을 제거한다.
		dragText = dragText.replaceAll(" ", "");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (dragText.contains("WHERE")) {
			resultMap = leftProcessingQueryCheck(dragText);
		}
		
		return resultMap;
	}

	static Map<String, Object> leftProcessingQueryCheck(String dragText) {
		if (dragText.contains("WHERESUBSTR")) return subStrProcessing(dragText);
		int tem = dragText.indexOf("WHERE");
		int whereLength = "WHERE".length();
		String t = dragText.substring(tem);
		if (t.contains("=")) {
			String operator = t.substring(whereLength, t.indexOf("="));
			if (operator.contains("*") || operator.contains("-") || operator.contains("+") || operator.contains("/")) return operationProcessing(dragText, operator); 
			
		}
		return null;
	}
	
	static Map<String, Object> operationProcessing(String dragText, String operator){
		
		return null;
		
	}
	
	/**
	* Method : subStrProcessing
	* 작성자 : 이중석
	* 변경이력 :
	* @param dragText
	* @return
	* Method 설명 : 좌변을 subStr으로 가공했을때
	*/
	static Map<String, Object> subStrProcessing(String dragText) {
		List<String> oldQList = new ArrayList<String>();
		List<String> newQList = new ArrayList<String>();
		List<String> oQList = new ArrayList<String>();
		List<String> nQList = new ArrayList<String>();
		
		String[] split  = dragText.split("WHERE");
		for(int i = 0; i < split.length; i++) {
			if (i != 0) {
				if (split[i].contains("')")) {
					split[i] = split[i].substring(0, split[i].lastIndexOf(")"));
				}
				if (split[i].contains(",")) {
					int endIdx = split[i].indexOf(",");
					if (split[i].contains("'")) {
						String valStr = split[i].substring(split[i].indexOf("'") + 1, split[i].lastIndexOf("'"));
						String colStr = split[i].substring(7, endIdx);
						String oldQuery=  split[i];
						oldQList.add(oldQuery);
						String newQuery = colStr + " LIKE '" + valStr + "%'";
						newQList.add(newQuery);
						dragText = dragText.replaceAll(oldQuery, newQuery);
					}
				}
				
				if (split[i].contains("AND")) {
					String[] sp = split[i].split("AND");
					for(String s : sp) {
						int eIdx = s.indexOf(",");
						String valStr2 = s.substring(s.indexOf("'") + 1, s.lastIndexOf("'"));
						String colStr2 = s.substring(7, eIdx);
						String oQ = s;
						oQList.add(oQ);
						String nQ = colStr2 + " LIKE '" + valStr2 + "%'";
						nQList.add(nQ);
						split[i] = split[i].replace(oQ, nQ);
					}
				}
			}
		}
		if (oQList.size() > 0) {
			for (int i = 0; i < oQList.size(); i++) {
				dragText = dragText.replace(oQList.get(i), nQList.get(i));
			}
		}
		if (oldQList.size() > 0) {
			for (int i = 0; i < oldQList.size(); i++) {
				dragText = dragText.replace(oldQList.get(i), newQList.get(i));
			}
		}
		dragText = dragText.replaceAll("SELECT", " SELECT ");
		dragText = dragText.replaceAll("FROM", "\n FROM ");
		dragText = dragText.replaceAll("WHERE", "\n WHERE ");
		dragText = dragText.replaceAll("AND", "\n AND ");
		dragText = dragText.replaceAll("=", " = ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", LEFT_PROCESSING);
		map.put("dragText", dragText);
		return map;
	}
}

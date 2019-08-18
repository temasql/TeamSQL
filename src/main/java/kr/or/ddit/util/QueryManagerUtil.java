/**
 * 
 */
package kr.or.ddit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private static final String NULLCHECK_PROCESSING = "데이터의 유무를 확인할때 COUNT를 사용하시면 해당테이블에 데이터가 많을 수록 성능이 저하됩니다.\n\n 해당 쿼리를 \n\n";
	private static final String CASECHECK_PROCESSING = "CASE또는 DECODE가 증손자 이상으로 중첩되었습니다. 연산자 계산에 cost가 발생하여 처리 속도가 떨어집니다.\n\n 해당 쿼리 사용을 권장하지 않습니다.\n\n";
	private static final Logger logger = LoggerFactory.getLogger(QueryManagerUtil.class);
	/**
	* Method : managerment
	* 작성자 : 이중석
	* 변경이력 :
	* @param dragText
	* @return
	* Method 설명 : 쿼리를 분석하여 가공해서 반환해주는 메서드
	*/
	public static Map<String, Object> managerment(String dragText) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 쿼리를 모두 대문자로 변환한다.
		try {
			dragText = dragText.toUpperCase();
		} catch (Exception e) {
			resultMap.put("result", "");
			return resultMap;
		}
		// 쿼리의 공백을 제거한다.
		dragText = dragText.replaceAll(" ", "");
		if (dragText.contains("CASEWHEN") || dragText.contains("DECODE")) {
			resultMap = decodeCaseCheck(dragText);
			for (String res : resultMap.keySet()) {
				logger.debug("res : {}", res);
			}
			return resultMap;
		}
		
		if (dragText.contains("SELECTCOUNT(*)")) {
			if(!dragText.contains("SELECTCOUNT(*),")) {
				resultMap =	countNullCheck(dragText);
			}
		}
		if (dragText.contains("WHERE")) {
			resultMap = leftProcessingQueryCheck(dragText);
		}
		
		return resultMap;
	}

	/**
	* Method : countNullCheck
	* 작성자 : 이중석
	* 변경이력 :
	* @param dragText
	* Method 설명 :
	*/
	private static Map<String, Object> countNullCheck(String dragText) {
		dragText = dragText.replaceAll("COUNT\\(\\*\\)", "*");
		dragText = dragText.replaceAll("SELECT", " SELECT ");
		dragText = dragText.replaceAll("FROM", "\n FROM ");
		dragText = dragText.replaceAll("WHERE", "\n WHERE ");
		dragText = dragText.replaceAll("AND", "\n AND ");
		dragText = dragText.replaceAll("=", " = ");
		String proc = "SELECT 1 AS CNT FROM DUAL WHERE EXISTS (" + dragText + ")";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", NULLCHECK_PROCESSING);
		map.put("dragText", proc);
		return map;
		
	}

	static Map<String, Object> leftProcessingQueryCheck(String dragText) {
		if (dragText.contains("WHERESUBSTR")) return subStrProcessing(dragText);
		int tem = dragText.indexOf("WHERE");
		int whereLength = "WHERE".length();
		String t = dragText.substring(tem);
		if (t.contains("=")) {
			String operator = t.substring(whereLength, t.lastIndexOf("="));
			if (operator.contains("*") || operator.contains("-") || operator.contains("+") || operator.contains("/")) return operationProcessing(dragText); 
				
		}
		return new HashMap<String, Object>();
	}
	
	static Map<String, Object> operationProcessing(String dragText){
		List<String> oldQList = new ArrayList<String>();
		List<String> newQList = new ArrayList<String>();
		List<String> check = new ArrayList<String>();
		String[] split  = dragText.split("WHERE");
		for(int i = 0; i < split.length; i++) {
			if (i != 0) {
				if (split[i].contains(")")) {
					split[i] = split[i].substring(0, split[i].lastIndexOf(")"));
				}
				
				if (split[i].contains("AND")) {
					String[] as = split[i].split("AND");
					for(String b : as) {
						String rVa = "";
						String leftVa = "";
						oldQList.add(b);
						String[] c = b.split("=");
						if (b.contains("*")) {
							leftVa = c[0].substring(0, c[0].indexOf("*"));
							String lefOpSt = c[0].substring(c[0].indexOf("*") + 1);
							rVa = c[1] + "/" + lefOpSt;
							String nQ = leftVa + "=" +  rVa;
							newQList.add(nQ);
							continue;
						} else if (b.contains("+")) {
							leftVa = c[0].substring(0, c[0].indexOf("+"));
							String lefOpSt = c[0].substring(c[0].indexOf("+") + 1);
							rVa = c[1] + "-" + lefOpSt;
							String nQ = leftVa + "=" +  rVa;
							newQList.add(nQ);
							continue;
						} else if (b.contains("-")) {
							leftVa = c[0].substring(0, c[0].indexOf("-"));
							String lefOpSt = c[0].substring(c[0].indexOf("-") + 1);
							rVa = c[1] + "+" + lefOpSt;
							String nQ = leftVa + "=" +  rVa;
							newQList.add(nQ);
							break;
						} else if (b.contains("/")) {
							leftVa = c[0].substring(0, c[0].indexOf("/"));
							String lefOpSt = c[0].substring(c[0].indexOf("/") + 1);
							rVa = c[1] + "*" + lefOpSt;
							String nQ = leftVa + "=" +  rVa;
							newQList.add(nQ);
							continue;
						}
					}
				}else {
					String[] rightValArr = split[i].split("=");
					String leftVar = "";
					if (rightValArr[0].contains("*")) {
						leftVar = rightValArr[0].substring(0, rightValArr[0].indexOf("*"));
						String leftOpStr = rightValArr[0].substring(rightValArr[0].indexOf("*") + 1);
						rightValArr[1] += "/" + leftOpStr;
						check.add("check"); 
						oldQList.add(split[i]);
						String newQuery = leftVar + "=" + rightValArr[1];
						newQList.add(newQuery);
						continue;
					} else if (rightValArr[0].contains("+")) {
						leftVar = rightValArr[0].substring(0, rightValArr[0].indexOf("+"));
						String leftOpStr = rightValArr[0].substring(rightValArr[0].indexOf("+") + 1);
						rightValArr[1] += "-" + leftOpStr;
						check.add("check"); 
						oldQList.add(split[i]);
						String newQuery = leftVar + "=" + rightValArr[1];
						newQList.add(newQuery);
						continue;
					} else if (rightValArr[0].contains("-")) {
						leftVar = rightValArr[0].substring(0, rightValArr[0].indexOf("-"));
						String leftOpStr = rightValArr[0].substring(rightValArr[0].indexOf("-") + 1);
						rightValArr[1] += "+" + leftOpStr;
						check.add("check"); 
						oldQList.add(split[i]);
						String newQuery = leftVar + "=" + rightValArr[1];
						newQList.add(newQuery);
						continue;
					} else if (rightValArr[0].contains("/")) {
						leftVar = rightValArr[0].substring(0, rightValArr[0].indexOf("/"));
						String leftOpStr = rightValArr[0].substring(rightValArr[0].indexOf("/") + 1);
						rightValArr[1] += "*" + leftOpStr;
						check.add("check"); 
						oldQList.add(split[i]);
						String newQuery = leftVar + "=" + rightValArr[1];
						newQList.add(newQuery);
						continue;
					}  
				}
			}
		}
		for (int j = 0; j < oldQList.size(); j++) {
			dragText = dragText.replace(oldQList.get(j), newQList.get(j));
		}
		dragText = dragText.replaceAll("SELECT", " SELECT ");
		dragText = dragText.replaceAll("FROM", "\n FROM ");
		dragText = dragText.replaceAll("WHERE", "\n WHERE ");
		dragText = dragText.replaceAll("AND", "\n AND ");
		dragText = dragText.replaceAll("=", " = ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", LEFT_PROCESSING);
		map.put("dragText", dragText);
		logger.debug("check.size : {}", check.size());
		if (check.size() == 0) {
			map.put("result", "");
		}
		return map;
		
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
	
	static Map<String, Object> decodeCaseCheck(String dragText){
		int caseCnt = caseCheck(dragText, 0) ;
		Map<String, Object> map = new HashMap<String, Object>();
		logger.debug("caseCnt : {}", caseCnt);
		if (caseCnt >= 3) {
			logger.debug("{}",CASECHECK_PROCESSING);
			map.put("result", CASECHECK_PROCESSING);
			map.put("dragText", "");
			return map;
		}else {
			map.put("result", "");
			map.put("dragText", dragText);
			return map;
		}
	}
	
	static int caseCheck(String dragText, int cnt) {
		if (dragText.contains("CASEWHEN")) {
				int caseIdx = dragText.indexOf("CASEWHEN");
				int caseEndIdx = caseIdx + 8;
				dragText = dragText.substring(caseIdx + caseEndIdx);
				cnt++;
				if (dragText.indexOf("CASEWHEN") != -1) {
					caseIdx = dragText.indexOf("CASEWHEN");
					dragText = dragText.substring(caseIdx);
				}
				if (dragText.startsWith("CASEWHEN")) {
					cnt = caseCheck(dragText, cnt +1);
				}
			}
			if (cnt >= 3) {
				return cnt;
			}
			return 0;
	}
}

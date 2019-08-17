/**
 * 
 */
package kr.or.ddit.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* SelectTableUtilTest.java
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
public class SelectTableUtilTest {

	private static final Logger logger = LoggerFactory.getLogger(SelectTableUtilTest.class);
	
//	@Test
//	public void test() {
//		/***Given***/
//		String select = SelectTableUtil.selectQuery("column", "USERS");
//		/***When***/
//		logger.debug("\n{}", select);
//		/***Then***/
//	}

	@Test
	public void queryManagerTest() {
		/***Given***/
		String query = "select * from users, (select * from sh where substr(hh, 1,2) = 'h' and substr(sub, 12, 3) = 'hs') where substr(user_id, 1, 4) = 'hi' and substr(zx, 12, 3) = 'hx'";
//		String query = "select * from users where usbstr(users, 1,2) = 'hi'";
		/***When***/
		// 쿼리를 모두 대문자로 변환한다.
		query = query.toUpperCase();
		logger.debug("upper : {}", query);
		
		// 쿼리의 공백을 제거한다.
		query = query.replaceAll(" ", "");
		
//		logger.debug("trim : {}", query);
		if (query.contains("WHERE")) {
			List<String> oldQList = new ArrayList<String>();
			List<String> newQList = new ArrayList<String>();
			List<String> oQList = new ArrayList<String>();
			List<String> nQList = new ArrayList<String>();
			String[] split  = query.split("WHERE");
			for(int i = 0; i < split.length; i++) {
				if (i != 0) {
					logger.debug("split : {}", split[i]);
					if (split[i].contains("')")) {
						split[i] = split[i].substring(0, split[i].lastIndexOf(")"));
						logger.debug("split[i] : {}", split[i]);
					}
					int endIdx = split[i].indexOf(",");
					logger.debug("split subStr : {}", split[i].substring(1, endIdx));
					String valStr = split[i].substring(split[i].indexOf("'") + 1, split[i].lastIndexOf("'"));
					String colStr = split[i].substring(7, endIdx);
					logger.debug("vlaStr : {}", valStr);
					logger.debug("colStr : {}",colStr);
					logger.debug("split[i] : {}", split[i]);
					logger.debug(colStr + " LIKE '" + valStr + "%'");
					String oldQuery=  split[i];
					oldQList.add(oldQuery);
					logger.debug("oldQ : {}", oldQuery);
					String newQuery = colStr + " LIKE '" + valStr + "%'";
					newQList.add(newQuery);
					logger.debug("newQ : {}", newQuery);
					logger.debug("{}", query.contains(oldQuery));
					query = query.replaceAll(oldQuery, newQuery);
					logger.debug("like 반환 : {}", query);
					
					if (split[i].contains("AND")) {
						String[] sp = split[i].split("AND");
						for(String s : sp) {
							logger.debug("s : {}", s);
							int eIdx = s.indexOf(",");
							String valStr2 = s.substring(s.indexOf("'") + 1, s.lastIndexOf("'"));
							String colStr2 = s.substring(7, eIdx);
							logger.debug("vlaStr2 : {}", valStr2);
							logger.debug("colStr2 : {}",colStr2);
							String oQ = s;
							oQList.add(oQ);
							logger.debug("oQ : {}", oQ);
							String nQ = colStr2 + " LIKE '" + valStr2 + "%'";
							nQList.add(nQ);
							logger.debug("nQ : {}", nQ);
							split[i] = split[i].replace(oQ, nQ);
						}
					}
				}
			}
			if (oQList.size() > 0) {
				for (int i = 0; i < oQList.size(); i++) {
					logger.debug("oqList : {}", nQList.get(i));
					logger.debug("nqList : {}", nQList.get(i));
					query = query.replace(oQList.get(i), nQList.get(i));
					logger.debug("listReplace  : {}",query.replace(oQList.get(i), nQList.get(i)));
				}
			}
			for (int i = 0; i < oldQList.size(); i++) {
				logger.debug("oList : {}", oldQList.get(i));
				logger.debug("nList : {}", newQList.get(i));
				query = query.replace(oldQList.get(i), newQList.get(i));
				logger.debug("listReplace  : {}",query.replace(oldQList.get(i), newQList.get(i)));
			}
			query = query.replaceAll("SELECT", " SELECT ");
			query = query.replaceAll("FROM", "\n FROM ");
			query = query.replaceAll("WHERE", "\n WHERE ");
			query = query.replaceAll("AND", "\n AND ");
			query = query.replaceAll("=", " = ");
			logger.debug("query : {}", query);
		}
		/***Then***/
	}
	
	@Test
	public void operationTest() {
		String dragText = "select * from users, (select * from asd where sal = 255000*20 ) where  bal = 35000000/20".toUpperCase();
		dragText = dragText.replaceAll(" ", "");
		
		List<String> oldQList = new ArrayList<String>();
		List<String> newQList = new ArrayList<String>();
		List<String> check = new ArrayList<String>();
		logger.debug("dragText : {}", dragText);
		
		
		String[] split  = dragText.split("WHERE");
		for(int i = 0; i < split.length; i++) {
			if (i != 0) {
				logger.debug("split : {}", split[i]);
//					oldQList.add(split[i]);
				if (split[i].contains(")")) {
					split[i] = split[i].substring(0, split[i].lastIndexOf(")"));
					
					logger.debug("fullQuery[i] : {}", split[i]);
				}
				
				if (split[i].contains("AND")) {
					String[] as = split[i].split("AND");
					logger.debug("asSIze : {}", as.length);
					for(String b : as) {
						String rVa = "";
						String leftVa = "";
						logger.debug("b : {}", b);
						oldQList.add(b);
						String[] c = b.split("=");
						logger.debug("d : {}", c[0]);
						logger.debug("leftVal : {}", b);
						if (b.contains("*")) {
							leftVa = c[0].substring(0, c[0].indexOf("*"));
							logger.debug("leftVa : {}", leftVa);
							String lefOpSt = c[0].substring(c[0].indexOf("*") + 1);
							logger.debug("lefOpst : {}", lefOpSt);
							rVa = c[1] + "/" + lefOpSt;
							String nQ = leftVa + "=" +  rVa;
							logger.debug("nqqQ : {}", nQ);
							newQList.add(nQ);
							continue;
						} else if (b.contains("+")) {
							leftVa = c[0].substring(0, c[0].indexOf("+"));
							logger.debug("leftVa : {}", leftVa);
							String lefOpSt = c[0].substring(c[0].indexOf("+") + 1);
							logger.debug("lefOpst : {}", lefOpSt);
							rVa = c[1] + "-" + lefOpSt;
							String nQ = leftVa + "=" +  rVa;
							logger.debug("nqqQ : {}", nQ);
							newQList.add(nQ);
							continue;
						} else if (b.contains("-")) {
							leftVa = c[0].substring(0, c[0].indexOf("-"));
							logger.debug("leftVa : {}", leftVa);
							String lefOpSt = c[0].substring(c[0].indexOf("-") + 1);
							logger.debug("lefOpst : {}", lefOpSt);
							rVa = c[1] + "+" + lefOpSt;
							String nQ = leftVa + "=" +  rVa;
							logger.debug("nqqQ : {}", nQ);
							newQList.add(nQ);
							break;
						} else if (b.contains("/")) {
							leftVa = c[0].substring(0, c[0].indexOf("/"));
							logger.debug("leftVa : {}", leftVa);
							String lefOpSt = c[0].substring(c[0].indexOf("/") + 1);
							logger.debug("lefOpst : {}", lefOpSt);
							rVa = c[1] + "*" + lefOpSt;
							String nQ = leftVa + "=" +  rVa;
							logger.debug("nqqQ : {}", nQ);
							newQList.add(nQ);
							continue;
						}
						logger.debug("rVa : {}", rVa);
//						String nQ = leftVa + "=" +  rVa;
//						logger.debug("nqqQ : {}", nQ);
//						newQList.add(nQ);
					}
				}else {
					String[] rightValArr = split[i].split("=");
					for (String rightVal : rightValArr) {
						logger.debug("rightVal : {}",rightVal);
						if (rightVal.contains("*") || rightVal.contains("-") 
								|| rightVal.contains("+")  || rightVal.contains("/")) {
							 
						}
					}
					String leftVar = "";
					if (rightValArr[0].contains("*")) {
						leftVar = rightValArr[0].substring(0, rightValArr[0].indexOf("*"));
						logger.debug("leftVar : {}", leftVar);
						String leftOpStr = rightValArr[0].substring(rightValArr[0].indexOf("*") + 1);
						logger.debug("leftOpStr : {}", leftOpStr);
						check.add("check");
						rightValArr[1] += "/" + leftOpStr;
						oldQList.add(split[i]);
						String newQuery = leftVar + "=" + rightValArr[1];
						newQList.add(newQuery);
						continue;
					} else if (rightValArr[0].contains("+")) {
						leftVar = rightValArr[0].substring(0, rightValArr[0].indexOf("+"));
						logger.debug("leftVar : {}", leftVar);
						String leftOpStr = rightValArr[0].substring(rightValArr[0].indexOf("+") + 1);
						logger.debug("leftOpStr : {}", leftOpStr);
						check.add("check");
						rightValArr[1] += "-" + leftOpStr;
						oldQList.add(split[i]);
						String newQuery = leftVar + "=" + rightValArr[1];
						newQList.add(newQuery);
						continue;
					} else if (rightValArr[0].contains("-")) {
						leftVar = rightValArr[0].substring(0, rightValArr[0].indexOf("-"));
						logger.debug("leftVar : {}", leftVar);
						String leftOpStr = rightValArr[0].substring(rightValArr[0].indexOf("-") + 1);
						logger.debug("leftOpStr : {}", leftOpStr);
						check.add("check");
						rightValArr[1] += "+" + leftOpStr;
						oldQList.add(split[i]);
						String newQuery = leftVar + "=" + rightValArr[1];
						newQList.add(newQuery);
						continue;
					} else if (rightValArr[0].contains("/")) {
						leftVar = rightValArr[0].substring(0, rightValArr[0].indexOf("/"));
						logger.debug("leftVar : {}", leftVar);
						String leftOpStr = rightValArr[0].substring(rightValArr[0].indexOf("/") + 1);
						logger.debug("leftOpStr : {}", leftOpStr);
						check.add("check");
						rightValArr[1] += "*" + leftOpStr;
						oldQList.add(split[i]);
						String newQuery = leftVar + "=" + rightValArr[1];
						newQList.add(newQuery);
						continue;
					}  
					logger.debug("rightValArr[1] : {}", rightValArr[1]);
				}
			}
		}
		for (int j = 0; j < oldQList.size(); j++) {
			logger.debug("oldql : {}", oldQList.get(j));
			logger.debug("newql : {}", newQList.get(j));
			dragText = dragText.replace(oldQList.get(j), newQList.get(j));
		}
		dragText = dragText.replaceAll("SELECT", " SELECT ");
		dragText = dragText.replaceAll("FROM", "\n FROM ");
		dragText = dragText.replaceAll("WHERE", "\n WHERE ");
		dragText = dragText.replaceAll("AND", "\n AND ");
		dragText = dragText.replaceAll("=", " = ");
		logger.debug("chSize : {}", check.size());
		logger.debug("proc : {}", dragText);
		
	}
	
	@Test
	public void countNullCheckTest() {
		String dragText = "select count(*) from users".toUpperCase();
		dragText = dragText.replaceAll(" ", "");
		dragText = dragText.replaceAll("COUNT\\(\\*\\)", "*");
		dragText = dragText.replaceAll("SELECT", " SELECT ");
		dragText = dragText.replaceAll("FROM", "\n FROM ");
		dragText = dragText.replaceAll("WHERE", "\n WHERE ");
		dragText = dragText.replaceAll("AND", "\n AND ");
		dragText = dragText.replaceAll("=", " = ");
		String proc = "SELECT 1 AS CNT FROM DUAL WHERE EXISTS (" + dragText + ")";
		logger.debug("proc : {}", proc);
	}
	
	@Test
	public void decodeCheckTest() {
		String dragText = "select a,  case when a case when b  case when c then end  then end  then end from asd where asd=ads ".toUpperCase();
		dragText = dragText.replaceAll(" ", "");
//		logger.debug("drt : {}", dragText);
//		int caseIdx = dragText.indexOf("CASEWHEN");
//		int caseEndIdx = caseIdx + 8;
//		dragText = dragText.substring(caseIdx + caseEndIdx);
//		logger.debug("caseEndidx 1 : {}", caseEndIdx);
//		logger.debug("dt1 : {}",dragText);
//		caseIdx = dragText.indexOf("CASEWHEN");
//		caseEndIdx = caseIdx + 8;
//		dragText = dragText.substring(caseIdx + caseEndIdx);
//		logger.debug("caseEndidx 2 : {}", caseEndIdx);
//		logger.debug("dt2 : {}",dragText);
//		caseIdx = dragText.indexOf("CASEWHEN");
//		caseEndIdx = caseIdx + 8;
//		dragText = dragText.substring(caseIdx + caseEndIdx);
//		logger.debug("caseEndidx 3 : {}", caseEndIdx);
//		logger.debug("dt3 : {}",dragText);
		logger.debug("hio : {}",caseCheck(dragText, 0));
		dragText = dragText.replaceAll("COUNT\\(\\*\\)", "*");
		dragText = dragText.replaceAll("SELECT", " SELECT ");
		dragText = dragText.replaceAll("FROM", "\n FROM ");
		dragText = dragText.replaceAll("WHERE", "\n WHERE ");
		dragText = dragText.replaceAll("AND", "\n AND ");
		dragText = dragText.replaceAll("=", " = ");
		String proc = "SELECT 1 AS CNT FROM DUAL WHERE EXISTS (" + dragText + ")";
		logger.debug("proc : {}", proc);
	}
	public int caseCheck(String dragText, int cnt) {
		 if (dragText.contains("CASEWHEN")) {
			 logger.debug("인");
				int caseIdx = dragText.indexOf("CASEWHEN");
				int caseEndIdx = caseIdx + 8;
				logger.debug("자르기 1 전 : {}", dragText);
				dragText = dragText.substring(caseIdx + caseEndIdx);
				cnt++;
				logger.debug("자르기 1 : {}", dragText);
				if (dragText.indexOf("CASEWHEN") != -1) {
					caseIdx = dragText.indexOf("CASEWHEN");
					dragText = dragText.substring(caseIdx);
					logger.debug("자르기 2 : {}", dragText);
				}
				if (dragText.startsWith("CASEWHEN")) {
					logger.debug("인 2");
					cnt = caseCheck(dragText, cnt +1);
				}
			}
			if (cnt >= 3) {
				return cnt;
			}
			return 0;
	}
	
}

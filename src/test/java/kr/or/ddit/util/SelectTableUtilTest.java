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
		String dragText = "select * from users, (select * from asd where sal * 30 = 255000) where  bal * 12 = 35000000".toUpperCase();
		dragText = dragText.replaceAll(" ", "");
		int tem = dragText.indexOf("WHERE");
		int whereLength = "WHERE".length();
		String t = dragText.substring(tem);
		String a = t.substring(whereLength, t.indexOf("="));
		logger.debug("dragText : {}", dragText);
		logger.debug("a : {}", a);
		if (a.contains("*")) {
			String[] split  = dragText.split("WHERE");
			for(int i = 0; i < split.length; i++) {
				if (i != 0) {
					logger.debug("split : {}", split[i]);
					if (split[i].contains(")")) {
						split[i] = split[i].substring(0, split[i].lastIndexOf(")"));
						logger.debug("split[i] : {}", split[i]);
					}
					String[] sp = split[i].split("=");
					for (String string : sp) {
						logger.debug("string : {}",string);
					}
					
					String leftVar = sp[0].substring(0, sp[0].indexOf("*"));
					logger.debug("leftVar : {}", leftVar);
					String leftOpStr = sp[0].substring(sp[0].indexOf("*") + 1);
					logger.debug("leftOpStr : {}", leftOpStr);
					sp[1] += "/" + leftOpStr;
					logger.debug("sp[1] : {}", sp[1]);
//					int endIdx = split[i].indexOf(",");
//					logger.debug("split subStr : {}", split[i].substring(1, endIdx));
//					String valStr = split[i].substring(split[i].indexOf("'") + 1, split[i].lastIndexOf("'"));
//					String colStr = split[i].substring(7, endIdx);
//					logger.debug("vlaStr : {}", valStr);
//					logger.debug("colStr : {}",colStr);
//					logger.debug("split[i] : {}", split[i]);
//					logger.debug(colStr + " LIKE '" + valStr + "%'");
				}
			}
		} else if (a.contains("-")) {
			
		} else if (a.contains("+")) {
			
		} else if(a.contains("/")) {
			
		}
	}
	
}

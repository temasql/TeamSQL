package kr.or.ddit.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {
	
	public List<List<CrawlingVO>> getITNews() {
		Connection.Response response;
		Document document = null;
		try {
			response = Jsoup.connect("http://www.itnews.or.kr/?cat=1162").method(Connection.Method.GET).execute();
			document = response.parse();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements parent = document.select("div[class=td-ss-main-content]").first().children();
		
		List<List<CrawlingVO>> resultList = new ArrayList<List<CrawlingVO>>();
		
		for (int i = 0; i < 3; i++) {
			Element subParent = parent.get(i).child(0);
			String title = subParent.child(1).child(0).child(0).child(0).text();
			String content = subParent.child(1).child(1).text();
			String img = subParent.child(0).child(0).child(0).child(0).attr("src");
			String link = subParent.child(1).child(0).child(0).child(0).attr("href");
			CrawlingVO cVO = new CrawlingVO(title, content, img, link);
			List<CrawlingVO> tempList = new ArrayList<CrawlingVO>();
			tempList.add(cVO);
			resultList.add(tempList);
		}
		
		return resultList;
	}
}

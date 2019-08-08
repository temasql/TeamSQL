package kr.or.ddit.testData.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.testData.dao.ITestDataDao;

@Service
public class TestDataService implements ITestDataService {

	@Resource(name = "testDataDao")
	private ITestDataDao testDataDao;
	
	@Override
	public List<String> getTestData(String[] column_name, String[] data_type, String[] isNull, 
																String table_name, int dataCnt) {
		
		List<String> nameList = testDataDao.getNameData();
		List<String> phoneList = testDataDao.getPhoneData();
		List<String> dateList = testDataDao.getDateData();
		List<String> countryList = testDataDao.getCountryData();
		List<String> emailList = testDataDao.getEmailData();
		List<String> cityList = testDataDao.getCityData();
		
		List<String> sysdateList = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			sysdateList.add("sysdate");
		}
		
		List<String> nullList = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			nullList.add("null");
		}
		
		String result = "INSERT INTO " + table_name + "(";
		String temp = "";
		
		for (int i = 0; i < dataCnt; i++) {
			temp += column_name[i] + ",";
		}
		
		temp = temp.substring(0, temp.lastIndexOf(","));
		
		result += temp + ") VALUES(";
		
		List<List<String>> list = new ArrayList<List<String>>();
		for (int i = 0; i < data_type.length; i++) {
			if(isNull[i].equals("NULL")) list.add(nullList);
			else if(data_type[i].equals("이름")) list.add(nameList);
			else if(data_type[i].equals("전화번호")) list.add(phoneList);
			else if(data_type[i].equals("이메일")) list.add(emailList);
			else if(data_type[i].equals("날짜")) list.add(dateList);
			else if(data_type[i].equals("날짜(오늘)")) list.add(sysdateList);
			else if(data_type[i].equals("국적")) list.add(countryList);
			else if(data_type[i].equals("도시(대한민국)")) list.add(cityList);
			else if(data_type[i].equals("커스텀"));
		}
		
		List<String> resultList = new ArrayList<String>();
		
		String tmp = "";
		for (int i = 0; i < dataCnt; i++) {
			for (int j = 0; j < list.size(); j++) {
				tmp += "'" + list.get(j).get(i) + "',";
			}
			int cnt = tmp.lastIndexOf(",");
			tmp = tmp.substring(0, cnt);
			resultList.add(result + tmp + ");");
		}
		return resultList;
	}

	
}

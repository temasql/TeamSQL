package kr.or.ddit.testData.service;

import java.util.List;



public interface ITestDataService {
		
	List<String> getTestData(String[] column_name, String[] data_type, String[] isNull, 
															String table_name, int dataCnt);
}

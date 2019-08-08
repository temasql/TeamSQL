/**
 * 
 */
package kr.or.ddit.testData.dao;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.stereotype.Repository;

@Repository
public class TestDataDao implements ITestDataDao {

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public List<String> getTestData(Map<String, Object> map) {
		return sqlSession.selectList("testData.getTestData", map);
	}

	@Override
	public List<String> getNameData() {
		return sqlSession.selectList("testData.getNameData");
	}

	@Override
	public List<String> getPhoneData() {
		return sqlSession.selectList("testData.getPhoneData");
	}

	@Override
	public List<String> getDateData() {
		return sqlSession.selectList("testData.getDateData");
	}

	@Override
	public List<String> getCountryData() {
		return sqlSession.selectList("testData.getCountryData");
	}

	@Override
	public List<String> getEmailData() {
		return sqlSession.selectList("testData.getEmailData");
	}

	@Override
	public List<String> getCityData() {
		return sqlSession.selectList("testData.getCityData");
	}
}

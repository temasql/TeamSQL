package kr.or.ddit.user.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.testenv.LogicTestEnv;

public class UserDaoTest extends LogicTestEnv{

	@Resource(name = "userDao")
	private IUserDao dao;
	
	@Test
	public void daoTest() {
		/***Given***/

		/***When***/
		
		/***Then***/
		assertEquals("", "");
	}

}

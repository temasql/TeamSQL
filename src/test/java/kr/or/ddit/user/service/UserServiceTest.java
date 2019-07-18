package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.testenv.LogicTestEnv;

public class UserServiceTest extends LogicTestEnv{

	@Resource(name = "userService")
	private IUserService service;
	
	@Test
	public void serviceTest() {
		/***Given***/

		/***When***/
		
		/***Then***/
		assertEquals("", "");
	}

}

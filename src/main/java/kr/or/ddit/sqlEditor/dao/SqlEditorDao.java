package kr.or.ddit.sqlEditor.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

//@Repository
public class SqlEditorDao implements ISqlEditorDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;


}

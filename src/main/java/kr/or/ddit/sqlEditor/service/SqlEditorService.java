package kr.or.ddit.sqlEditor.service;

import javax.annotation.Resource;

import kr.or.ddit.sqlEditor.dao.ISqlEditorDao;

//@Service
public class SqlEditorService implements ISqlEditorService{

	@Resource(name = "sqlEditorDao")
	private ISqlEditorDao sqlEditorDao;

}

package org.wyjc.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.wyjc.dao.PageConfDao;
import org.wyjc.entity.PageConf;

public class PageConfDaoImpl extends SqlMapClientTemplate implements PageConfDao {
	private SqlMapClientTemplate sqlMapClientTemplate;

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	
	@Override
	public PageConf getPageConfById(String id) {
		return (PageConf) this.sqlMapClientTemplate.queryForObject("getPageConfById", id);
	}

	@Override
	public long insertPageConf(PageConf conf) {
		sqlMapClientTemplate.insert("insertPageConf", conf);
		return conf.getId(); 
	}

	@Override
	public void updatePageConfById(PageConf conf) {
		sqlMapClientTemplate.update("updatePageConfById", conf);
		
	}

	@Override
	public List<PageConf> getPageConfListByUserId(Long userId) {
		return sqlMapClientTemplate.queryForList("getPageConfListByUserId", userId);
	}

}

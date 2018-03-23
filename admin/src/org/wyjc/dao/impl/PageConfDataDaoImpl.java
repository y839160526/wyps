package org.wyjc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.wyjc.dao.PageConfDataDao;
import org.wyjc.entity.PageConfData;
import org.wyjc.entity.Pager;

public class PageConfDataDaoImpl extends SqlMapClientTemplate implements PageConfDataDao {
	private SqlMapClientTemplate sqlMapClientTemplate;

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	
	@Override
	public List<PageConfData> getPageConfDataByConfId(String confId) {
		List<PageConfData> list = null;
		list= this.sqlMapClientTemplate.queryForList("getPageConfDataByConfId", confId);
		return list;
	}

	@Override
	public void insertPageConfData(PageConfData data) {
		this.sqlMapClientTemplate.insert("insertPageConfData", data);
		
	}

	@Override
	public long getPageConfDataCountById(String confId) {
		return (long) sqlMapClientTemplate.queryForObject("getPageConfDataCountById", confId);
	}

	@Override
	public List<PageConfData> getPageConfDataByPager(Pager pager) {
		List<PageConfData> list=new ArrayList<PageConfData>();
		list=sqlMapClientTemplate.queryForList("getPageConfDataByPager", pager);
		return list;
	}

	@Override
	public void deleteConfData(String id) {
		 this.sqlMapClientTemplate.delete("deleteConfData",id);
		
	}

}

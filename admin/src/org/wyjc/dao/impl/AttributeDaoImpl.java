package org.wyjc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.wyjc.dao.AttributeDao;
import org.wyjc.entity.AttributeDTO;

public class AttributeDaoImpl extends SqlMapClientTemplate implements AttributeDao{
   private SqlMapClientTemplate sqlMapClientTemplate;
   
	public SqlMapClientTemplate getSqlMapClientTemplate() {
	return sqlMapClientTemplate;
}

public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
	this.sqlMapClientTemplate = sqlMapClientTemplate;
}

	public List<AttributeDTO> list(String type) {
		List<AttributeDTO> list=null;
		try {
			list=this.getSqlMapClient().queryForList("findAttributeByType",type);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	

}

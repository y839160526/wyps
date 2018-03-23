package org.wyjc.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.wyjc.dao.AreaDao;
import org.wyjc.entity.AreaDTO;
import org.wyjc.entity.CityDTO;
import org.wyjc.entity.ProvinceDTO;

public class AreaDaoImpl extends SqlMapClientTemplate implements AreaDao {
	private SqlMapClientTemplate sqlMapClientTemplate;

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProvinceDTO> getProvinces() {
		return this.sqlMapClientTemplate.queryForList("getProvinces");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CityDTO> getCityesByProvinceId(String provinceId) {
		return this.sqlMapClientTemplate.queryForList("getCityesByProvinceId",provinceId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AreaDTO> getAreasByCityId(String cityId) {
		return this.sqlMapClientTemplate.queryForList("getAreasByCityId",cityId);
	}


	

}

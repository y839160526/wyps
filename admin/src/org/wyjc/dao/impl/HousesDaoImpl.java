package org.wyjc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.wyjc.dao.HousesDao;
import org.wyjc.entity.HousesDTO;
import org.wyjc.util.Pager;

public class HousesDaoImpl extends SqlMapClientTemplate implements HousesDao{
   private SqlMapClientTemplate sqlMapClientTemplate;
   
	public SqlMapClientTemplate getSqlMapClientTemplate() {
	return sqlMapClientTemplate;
}

public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
	this.sqlMapClientTemplate = sqlMapClientTemplate;
}

	public List<HousesDTO> list() {
		List<HousesDTO> list=null;
		try {
			list=this.getSqlMapClient().queryForList("findAllHouses");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void insert(HousesDTO housesDTO) {
		try {
			this.getSqlMapClient().insert("insertHouses",housesDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(HousesDTO housesDTO) {
		try {
			this.getSqlMapClient().insert("deleteHouses",housesDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public HousesDTO get(int id) {
		HousesDTO housesDTO=null;
		housesDTO=(HousesDTO) this.sqlMapClientTemplate.queryForObject("getHousesById", id);
		return housesDTO;
	}

	public int getTotalCount() {
		int count=0;
		List<HousesDTO> list=null;
		try {
			list=this.getSqlMapClient().queryForList("findAllHouses");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list.size();
	}

	public List<HousesDTO> listByPager(Pager pager) {
		List<HousesDTO> list=null;
		try {
			list=this.getSqlMapClient().queryForList("findAllHousesByPager",pager);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}

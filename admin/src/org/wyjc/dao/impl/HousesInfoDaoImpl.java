package org.wyjc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.wyjc.dao.HousesInfoDao;
import org.wyjc.entity.CustomerDTO;
import org.wyjc.entity.HousesInfoDTO;
import org.wyjc.util.Pager;

public class HousesInfoDaoImpl extends SqlMapClientTemplate implements
		HousesInfoDao {
	private SqlMapClientTemplate sqlMapClientTemplate;

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	public List<HousesInfoDTO> list() {
		List<HousesInfoDTO> list = null;
		try {
			list = this.getSqlMapClient().queryForList("findAllHousesInfo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void insert(HousesInfoDTO housesInfoDTO) {
		try {
			this.getSqlMapClient().insert("insertHousesInfo", housesInfoDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(HousesInfoDTO housesInfoDTO) {
		try {
			this.getSqlMapClient().insert("deleteHousesInfo", housesInfoDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public HousesInfoDTO get(int id) {
		HousesInfoDTO housesInfoDTO = null;
		housesInfoDTO = (HousesInfoDTO) this.sqlMapClientTemplate
				.queryForObject("getHousesInfoById", id);
		return housesInfoDTO;
	}

	public int getTotalCount() {
		int count = 0;
		List<CustomerDTO> list = null;
		try {
			list = this.getSqlMapClient().queryForList("findAllHousesInfo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list.size();
	}

	public List<HousesInfoDTO> listByPager(Pager pager) {
		List<HousesInfoDTO> list = null;
		try {
			list = this.getSqlMapClient().queryForList(
					"findAllHousesInfoByPager", pager);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}

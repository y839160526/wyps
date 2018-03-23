package org.wyjc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.wyjc.dao.BuildingInfoDao;
import org.wyjc.entity.BuildingInfoDTO;
import org.wyjc.util.Pager;

public class BuildingInfoDaoImpl extends SqlMapClientTemplate implements BuildingInfoDao{
   private SqlMapClientTemplate sqlMapClientTemplate;
   
	public SqlMapClientTemplate getSqlMapClientTemplate() {
	return sqlMapClientTemplate;
}

public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
	this.sqlMapClientTemplate = sqlMapClientTemplate;
}

	public List<BuildingInfoDTO> list() {
		List<BuildingInfoDTO> list=null;
		try {
			list=this.getSqlMapClient().queryForList("findAllBuildingInfo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void insert(BuildingInfoDTO buildingInfoDTO) {
		try {
			this.getSqlMapClient().insert("insertBuildingInfo",buildingInfoDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(BuildingInfoDTO buildingInfoDTO) {
		try {
			this.getSqlMapClient().insert("deleteBuildingInfo",buildingInfoDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public BuildingInfoDTO get(int id) {
		BuildingInfoDTO buildingInfoDTO=null;
		buildingInfoDTO=(BuildingInfoDTO) this.sqlMapClientTemplate.queryForObject("getBuildingInfoById", id);
		return buildingInfoDTO;
	}

	public int getTotalCount() {
		int count=0;
		List<BuildingInfoDTO> list=null;
		try {
			list=this.getSqlMapClient().queryForList("findAllBuildingInfo");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list.size();
	}

	public List<BuildingInfoDTO> listByPager(Pager pager) {
		List<BuildingInfoDTO> list=null;
		try {
			list=this.getSqlMapClient().queryForList("findAllBuildingInfoByPager",pager);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}

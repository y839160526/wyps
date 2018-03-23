package org.wyjc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wyjc.dao.BuildingInfoDao;
import org.wyjc.entity.BuildingInfoDTO;
import org.wyjc.service.BuildingInfoService;
import org.wyjc.util.Pager;

@Service
public class BuildingInfoServiceImpl implements BuildingInfoService {
	@Resource
	private BuildingInfoDao buildingInfoDao;

	public BuildingInfoDao getBuildingInfoDao() {
		return buildingInfoDao;
	}

	public void setBuildingInfoDao(BuildingInfoDao buildingInfoDao) {
		this.buildingInfoDao = buildingInfoDao;
	}

	public List<BuildingInfoDTO> list() {
		return this.buildingInfoDao.list();
	}

	public void insert(BuildingInfoDTO buildingInfoDTO) {
		this.buildingInfoDao.insert(buildingInfoDTO);

	}

	public void delete(BuildingInfoDTO buildingInfoDTO) {
		this.buildingInfoDao.delete(buildingInfoDTO);
	}

	public BuildingInfoDTO get(int id) {
		// TODO Auto-generated method stub
		return this.buildingInfoDao.get(id);
	}


	public Pager listByPager(Pager pager) {
		Pager p=pager;
		p.setList(this.buildingInfoDao.listByPager(pager));
		p.setTotalCount(this.buildingInfoDao.getTotalCount());
		return p;
	}

}

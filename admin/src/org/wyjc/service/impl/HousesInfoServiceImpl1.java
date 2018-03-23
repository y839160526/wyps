package org.wyjc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wyjc.dao.HousesInfoDao;
import org.wyjc.entity.HousesInfoDTO;
import org.wyjc.service.HousesInfoService;
import org.wyjc.util.Pager;

@Service
public class HousesInfoServiceImpl1 implements HousesInfoService {
	@Resource
	private HousesInfoDao housesInfoDao;

	public HousesInfoDao getHousesInfoDao() {
		return housesInfoDao;
	}

	public void setHousesInfoDao(HousesInfoDao housesInfoDao) {
		this.housesInfoDao = housesInfoDao;
	}

	public List<HousesInfoDTO> list() {
		return this.housesInfoDao.list();
	}

	public void insert(HousesInfoDTO housesInfoDTO) {
		this.housesInfoDao.insert(housesInfoDTO);

	}

	public void delete(HousesInfoDTO housesInfoDTO) {
		this.housesInfoDao.delete(housesInfoDTO);
	}

	public HousesInfoDTO get(int id) {
		return this.housesInfoDao.get(id);
	}


	public Pager listByPager(Pager pager) {
		Pager p=pager;
		p.setList(this.housesInfoDao.listByPager(pager));
		p.setTotalCount(this.housesInfoDao.getTotalCount());
		return p;
	}

}

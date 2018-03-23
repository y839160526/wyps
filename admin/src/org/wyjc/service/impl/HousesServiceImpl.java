package org.wyjc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wyjc.dao.HousesDao;
import org.wyjc.entity.HousesDTO;
import org.wyjc.entity.CustomerDTO;
import org.wyjc.service.HousesService;
import org.wyjc.util.Pager;

@Service
public class HousesServiceImpl implements HousesService {
	@Resource
	private HousesDao housesDao;

	

	public HousesDao getHousesDao() {
		return housesDao;
	}

	public void setHousesDao(HousesDao housesDao) {
		this.housesDao = housesDao;
	}

	public List<HousesDTO> list() {
		return this.housesDao.list();
	}

	public void insert(HousesDTO housesDTO) {
		this.housesDao.insert(housesDTO);

	}

	public void delete(HousesDTO housesDTO) {
		this.housesDao.delete(housesDTO);
	}

	public HousesDTO get(int id) {
		return this.housesDao.get(id);
	}


	public Pager listByPager(Pager pager) {
		Pager p=pager;
		p.setList(this.housesDao.listByPager(pager));
		p.setTotalCount(this.housesDao.getTotalCount());
		return p;
	}

}
